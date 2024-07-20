package ru.alfastrah.embedi.tick.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.alfastrah.embedi.tick.dao.repos.AddressRepository;
import ru.alfastrah.embedi.tick.dao.repos.PersonRepository;
import ru.alfastrah.embedi.tick.dao.repos.TickQuoteRepository;
import ru.alfastrah.embedi.tick.models.TickQuote;

@Component
public class TickDao {

    private final TickQuoteRepository tickQuoteRepository;
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public TickDao(TickQuoteRepository tickQuoteRepository, AddressRepository addressRepository,
            PersonRepository personRepository) {
        this.tickQuoteRepository = tickQuoteRepository;
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public TickQuote saveQuote(TickQuote quoteToSave) {
        return quoteToSave;
    }
}
