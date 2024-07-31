package ru.alfastrah.embedi.tick.dao.repos;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ru.alfastrah.embedi.tick.dao.models.ContactRow;

@Repository
public interface ContactRepository extends ReactiveCrudRepository<ContactRow, UUID> {

}
