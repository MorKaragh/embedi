package ru.alfastrah.embedi.tick.dao.repos;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import ru.alfastrah.embedi.tick.dao.models.TickLinkerPersonRow;

@Repository
public interface TickLinkerPersonsReposityro extends ReactiveCrudRepository<TickLinkerPersonRow, UUID> {

    Flux<TickLinkerPersonRow> findAllByQuoteId(UUID quoteId);
    
}
