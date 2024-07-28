package ru.alfastrah.embedi.tick.dao;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.tick.dao.models.AddressRow;
import ru.alfastrah.embedi.tick.dao.models.PersonRow;
import ru.alfastrah.embedi.tick.dao.models.RowMappers;
import ru.alfastrah.embedi.tick.dao.models.TickLinkerPersonRow;
import ru.alfastrah.embedi.tick.dao.models.TickQuoteRow;
import ru.alfastrah.embedi.tick.dao.repos.AddressRepository;
import ru.alfastrah.embedi.tick.dao.repos.PersonRepository;
import ru.alfastrah.embedi.tick.dao.repos.TickLinkerPersonsReposityro;
import ru.alfastrah.embedi.tick.dao.repos.TickQuoteRepository;
import ru.alfastrah.embedi.tick.models.Person;
import ru.alfastrah.embedi.tick.models.TickQuote;

@Component
public class TickDao {

    private Logger logger = LoggerFactory.getLogger(TickDao.class);

    private final TickQuoteRepository tickQuoteRepository;
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;
    private final TickLinkerPersonsReposityro linkedPersonsRepository;

    public TickDao(TickQuoteRepository tickQuoteRepository,
            AddressRepository addressRepository,
            PersonRepository personRepository,
            TickLinkerPersonsReposityro linkedPersonsRepository) {
        this.tickQuoteRepository = tickQuoteRepository;
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.linkedPersonsRepository = linkedPersonsRepository;
    }

    @Transactional
    public Mono<TickQuote> saveQuote(TickQuote quoteToSave) {
        List<PersonRow> personRecords = quoteToSave.getInsuredPersons().stream().map(RowMappers::toPersonRow).toList();
        PersonRow insurerRow = RowMappers.toPersonRow(quoteToSave.getInsurer());
        AddressRow addressRow = RowMappers.toAddressRow(quoteToSave.getAddress());
        TickQuoteRow tickQuoteRow = RowMappers.toTickQuoteRow(quoteToSave);
        return Mono.zip(personRepository.save(insurerRow), addressRepository.save(addressRow))
                .flatMap(data -> {
                    tickQuoteRow.setInsurerId(data.getT1().getId());
                    tickQuoteRow.setAddress_id(data.getT2().getId());
                    return tickQuoteRepository.save(tickQuoteRow);
                }).flatMap(t -> {
                    return personRepository.saveAll(personRecords)
                            .flatMap(savedPersons -> {
                                var link = new TickLinkerPersonRow();
                                link.setQuoteId(t.getId());
                                link.setPersonId(savedPersons.getId());
                                return linkedPersonsRepository.save(link);
                            })
                            .then(Mono.just(t));
                })
                .flatMap(savedQuoteRow -> {
                    quoteToSave.setId(savedQuoteRow.getId());
                    return Mono.just(quoteToSave);
                });
    }

    public Mono<TickQuote> findById(UUID tickQuoteId) {
        return Mono.zip(loadQuoteMainData(tickQuoteId), loadInsuredPersons(tickQuoteId))
                .flatMap(tuple -> {
                    tuple.getT1().setInsuredPersons(tuple.getT2());
                    return Mono.just(tuple.getT1());
                })
                .flatMap(quote -> {
                    return addressRepository.findById(quote.getAddress().getId())
                            .map(addr -> {
                                quote.setAddress(RowMappers.toAddress(addr));
                                return quote;
                            });
                }).flatMap(quote -> {
                    return personRepository.findById(quote.getInsurer().getId())
                            .map(insurer -> {
                                quote.setInsurer(RowMappers.toPerson(insurer));
                                return quote;
                            });
                });
    }

    private Mono<TickQuote> loadQuoteMainData(UUID tickQuoteId) {
        return tickQuoteRepository.findById(tickQuoteId)
                .map(RowMappers::toTickQuote);
    }

    private Mono<List<Person>> loadInsuredPersons(UUID tickQuoteId) {
        return linkedPersonsRepository.findAllByQuoteId(tickQuoteId)
                .map(link -> link.getPersonId())
                .collectList()
                .flatMap(links -> personRepository.findAllById(links).collectList())
                .map(persons -> persons.stream().map(RowMappers::toPerson).toList());
    }

}
