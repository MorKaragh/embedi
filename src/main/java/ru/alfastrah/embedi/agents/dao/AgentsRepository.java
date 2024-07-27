package ru.alfastrah.embedi.agents.dao;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.agents.dao.models.AgentRecord;

@Repository
public interface AgentsRepository extends ReactiveCrudRepository<AgentRecord, UUID> {

    Mono<AgentRecord> findByIdAndStatus(UUID id, Integer status);

}
