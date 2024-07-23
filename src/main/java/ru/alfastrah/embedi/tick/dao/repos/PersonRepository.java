package ru.alfastrah.embedi.tick.dao.repos;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import ru.alfastrah.embedi.tick.dao.models.PersonRow;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<PersonRow, UUID> {

    Flux<PersonRow> findByFirstNameAndLastNameAndBirthDate(String firstName, String lastName, LocalDate birthDate);

}
