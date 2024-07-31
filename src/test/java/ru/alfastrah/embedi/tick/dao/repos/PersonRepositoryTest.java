package ru.alfastrah.embedi.tick.dao.repos;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import reactor.core.publisher.Flux;
import ru.alfastrah.embedi.tick.dao.models.PersonRow;
import ru.alfastrah.embedi.tick.dao.models.PersonRowTestData;

@DataR2dbcTest
@Testcontainers
@TestPropertySource(locations = "classpath:application-test.properties")
public class PersonRepositoryTest {

    static Logger logger = LoggerFactory.getLogger(PersonRepositoryTest.class);
    static Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(logger);

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.3"))
            .withPassword("testpass")
            .withUsername("testusr");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://"
                        + postgres.getHost() + ":" + postgres.getFirstMappedPort()
                        + "/" + postgres.getDatabaseName(), postgres.getUsername(), postgres.getPassword())
                .locations("classpath:db_schema/migrations")
                .baselineOnMigrate(true)
                .load();
        flyway.migrate();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.r2dbc.url", () -> "r2dbc:postgresql://"
                + postgres.getHost() + ":" + postgres.getFirstMappedPort()
                + "/" + postgres.getDatabaseName());
        registry.add("spring.r2dbc.username", () -> postgres.getUsername());
        registry.add("spring.r2dbc.password", () -> postgres.getPassword());
    }

    @Autowired
    private PersonRepository repository;

    @Test
    void testFindByFirstNameAndLastNameAndBirthDate() {
        postgres.followOutput(logConsumer);
        PersonRow fake = PersonRowTestData.makeFake(1);
        repository.save(fake).block();
        Flux<PersonRow> found = repository.findByFirstNameAndLastNameAndBirthDate(
                fake.getFirstName(),
                fake.getLastName(),
                fake.getBirthDate());
        List<PersonRow> foundList = found.collectList().block();
        assertFalse(foundList.isEmpty());
    }

}
