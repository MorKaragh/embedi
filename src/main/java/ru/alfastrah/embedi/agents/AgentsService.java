package ru.alfastrah.embedi.agents;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.agents.api.models.AgentMapper;
import ru.alfastrah.embedi.agents.api.models.AgentNotFoundException;
import ru.alfastrah.embedi.agents.dao.AgentsRepository;
import ru.alfastrah.embedi.agents.models.Agent;
import ru.alfastrah.embedi.agents.models.AgentStatus;

@Service
public class AgentsService {

    private final Logger logger = LoggerFactory.getLogger(AgentsService.class);

    private final AgentsRepository repository;

    public AgentsService(AgentsRepository repository) {
        this.repository = repository;
    }

    public Mono<Boolean> checkAgentIsActive(UUID agentId) {
        logger.info("проверка агента {}", agentId);
        return repository.findByIdAndStatus(agentId, AgentStatus.ACTIVE.getValue())
                .map(row -> true)
                .switchIfEmpty(Mono.error(new AgentNotFoundException()));
    }

    public Mono<Agent> saveAgent(Agent agent) {
        return repository
                .save(AgentMapper.toAgentRow(agent))
                .map(saved -> AgentMapper.toAgent(saved));
    }

}
