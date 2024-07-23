package ru.alfastrah.embedi.tick.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.tick.dao.models.AddressRow;
import ru.alfastrah.embedi.tick.dao.models.PersonRow;
import ru.alfastrah.embedi.tick.dao.models.RowMappers;
import ru.alfastrah.embedi.tick.dao.models.TickQuoteRow;
import ru.alfastrah.embedi.tick.dao.repos.AddressRepository;
import ru.alfastrah.embedi.tick.dao.repos.PersonRepository;
import ru.alfastrah.embedi.tick.dao.repos.TickLinkedPersonsRepository;
import ru.alfastrah.embedi.tick.dao.repos.TickQuoteRepository;
import ru.alfastrah.embedi.tick.models.TickQuote;

@Component
public class TickDao {

    private final TickQuoteRepository tickQuoteRepository;
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;
    private final TickLinkedPersonsRepository linkedPersonsRepository;

    public TickDao(TickQuoteRepository tickQuoteRepository,
            AddressRepository addressRepository,
            PersonRepository personRepository,
            TickLinkedPersonsRepository linkedPersonsRepository) {
        this.tickQuoteRepository = tickQuoteRepository;
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.linkedPersonsRepository = linkedPersonsRepository;
    }

    @Transactional
    public Mono<TickQuote> saveQuote(TickQuote quoteToSave) {
        List<PersonRow> personRecords = quoteToSave.getInsuredPersons().stream().map(RowMappers::personRow).toList();
        PersonRow insurerRow = RowMappers.personRow(quoteToSave.getInsurer());
        AddressRow addressRow = RowMappers.addressRow(quoteToSave.getAddress());
        TickQuoteRow tickQuoteRow = RowMappers.tickQuoteRow(quoteToSave);
        Mono<PersonRow> savedInsurer = personRepository.save(insurerRow);

        Flux<PersonRow> saveAll = personRepository.saveAll(personRecords);
        return null;
    }
}
