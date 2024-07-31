package ru.alfastrah.embedi.tick.dao;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import ru.alfastrah.embedi.agents.dao.AgentsRepository;
import ru.alfastrah.embedi.agents.dao.models.AgentRow;
import ru.alfastrah.embedi.agents.models.AgentStatus;
import ru.alfastrah.embedi.tick.models.TickQuote;
import ru.alfastrah.embedi.tick.models.TickQuoteTestData;

@SpringBootTest
@Testcontainers
public class TickDaoTest {

    static Logger logger = LoggerFactory.getLogger(TickDaoTest.class);
    static Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(logger);

    @Autowired
    private TickDao dao;

    @Autowired
    private AgentsRepository agentsRepository;

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.3"))
            .withPassword("testpass")
            .withUsername("testusx");

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
        registry.add("spring.flyway.url", () -> "jdbc:postgresql://"
                + postgres.getHost() + ":" + postgres.getFirstMappedPort()
                + "/" + postgres.getDatabaseName());
    }

    @Test
    void testCreates() {
        assertNotNull(dao);
    }

    @Test
    void testSaveLoadQuote() {
        AgentRow agent = new AgentRow();
        agent.setName("AgentName");
        agent.setStatus(AgentStatus.ACTIVE);
        AgentRow savedAgent = agentsRepository.save(agent).block();
        TickQuote toSave = TickQuoteTestData.makeFake();
        assertNull(toSave.getId());
        toSave.setAgentId(savedAgent.getId());
        TickQuote saved = dao.saveQuote(toSave).block();
        assertNotNull(saved.getId());
        TickQuote loaded = dao.findById(saved.getId()).block();
        assertNotNull(loaded);
    }

}
