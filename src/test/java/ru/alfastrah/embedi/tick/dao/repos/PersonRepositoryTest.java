package ru.alfastrah.embedi.tick.dao.repos;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Flux;
import ru.alfastrah.embedi.tick.dao.models.PersonRecord;
import ru.alfastrah.embedi.tick.dao.models.PersonRecordTest;

public class PersonRepositoryTest extends AbstractRepoContaineredTest{

    @Autowired
    private PersonRepository repository;

    @Test
    void testFindByFirstNameAndLastNameAndBirthDate() {
        postgres.followOutput(logConsumer);
        System.out.println(postgres.getJdbcUrl());
        PersonRecord fake = PersonRecordTest.makeFake(1);
        repository.save(fake).block();
        Flux<PersonRecord> found = repository.findByFirstNameAndLastNameAndBirthDate(
                fake.getFirstName(),
                fake.getLastName(),
                fake.getBirthDate());
        List<PersonRecord> foundList = found.collectList().block();
        assertFalse(foundList.isEmpty());
    }

}
