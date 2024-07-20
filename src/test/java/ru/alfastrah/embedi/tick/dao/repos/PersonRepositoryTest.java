package ru.alfastrah.embedi.tick.dao.repos;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import reactor.core.publisher.Flux;
import ru.alfastrah.embedi.tick.dao.models.PersonRecord;
import ru.alfastrah.embedi.tick.dao.models.PersonRecordTest;

@DataR2dbcTest
@Testcontainers
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.3"))
            .withExposedPorts(5443)
            .withPassword("inmemory")
            .withUsername("inmemory");

    @Test
    void testFindByFirstNameAndLastNameAndBirthDate() {
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
