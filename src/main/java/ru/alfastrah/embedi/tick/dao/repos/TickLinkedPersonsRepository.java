package ru.alfastrah.embedi.tick.dao.repos;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ru.alfastrah.embedi.tick.dao.models.AddressRow;

@Repository
public interface TickLinkedPersonsRepository extends ReactiveCrudRepository<AddressRow, UUID> {

    
}
