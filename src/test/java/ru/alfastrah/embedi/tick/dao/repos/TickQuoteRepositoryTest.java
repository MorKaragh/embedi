package ru.alfastrah.embedi.tick.dao.repos;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import ru.alfastrah.embedi.agents.dao.AgentsRepository;
import ru.alfastrah.embedi.agents.dao.models.AgentRecord;
import ru.alfastrah.embedi.agents.dao.models.AgentStatus;
import ru.alfastrah.embedi.tick.dao.models.TickQuoteRow;
import ru.alfastrah.embedi.tick.dao.models.TickQuoteRowTestData;

@DataR2dbcTest
@Testcontainers
public class TickQuoteRepositoryTest {

    static Logger logger = LoggerFactory.getLogger(PersonRepositoryTest.class);
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
    private TickQuoteRepository tickQuoteRepository;

    @Autowired
    private AgentsRepository agentsRepository;

    @Test
    public void testSave() {
        AgentRecord agent = new AgentRecord();
        agent.setName("fakeAgent");
        agent.setStatus(AgentStatus.ACTIVE);
        agent = agentsRepository.save(agent).block();
        TickQuoteRow fake = TickQuoteRowTestData.makeFake();
        fake.setAgentId(agent.getId());
        TickQuoteRow record = tickQuoteRepository.save(fake).block();
        assertNotNull(record);
        assertNotNull(record.getId());

    }
}
