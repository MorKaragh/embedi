package ru.alfastrah.embedi.agents;

import java.util.UUID;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.agents.dao.AgentRecord;
import ru.alfastrah.embedi.agents.dao.AgentsRepository;

@Service
public class AgentsService {

    private final AgentsRepository repository;

    public AgentsService(AgentsRepository repository) {
        this.repository = repository;
    }

    public Mono<AgentRecord> getAgentById(UUID agentId) {
        return repository.findById(agentId);
    }

    public Mono<AgentRecord> saveAgent(AgentRecord agent) {
        return repository.save(agent);
    }

}
