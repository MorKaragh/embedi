package ru.alfastrah.embedi.tick.dao;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.agents.dao.AgentsRepository;
import ru.alfastrah.embedi.agents.dao.models.AgentRow;
import ru.alfastrah.embedi.agents.models.AgentStatus;
import ru.alfastrah.embedi.tick.dao.models.AddressRow;
import ru.alfastrah.embedi.tick.dao.models.RowMappers;
import ru.alfastrah.embedi.tick.dao.repos.AddressRepository;
import ru.alfastrah.embedi.tick.models.TickQuote;
import ru.alfastrah.embedi.tick.models.TickQuoteTestData;

@SpringBootTest
public class TickDaoTest {


    static Logger logger = LoggerFactory.getLogger(TickDaoTest.class);
    static Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(logger);

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.3"))
            .withCopyFileToContainer(MountableFile.forClasspathResource("schema.sql"), "/docker-entrypoint-initdb.d/")
            .withPassword("testpass")
            .withUsername("testusr");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
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
    private TickDao dao;

    @Autowired
    private AgentsRepository agentsRepository;

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
