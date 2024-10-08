package ru.alfastrah.embedi.tick.dao.repos;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import ru.alfastrah.embedi.tick.dao.models.AddressRow;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<AddressRow, UUID> {

    Flux<AddressRow> findByFullAddressString(String fullAddressString);
    
}
