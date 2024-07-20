package ru.alfastrah.embedi.agents.dao;

import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgentsRepository extends ReactiveCrudRepository<AgentRecord, UUID> {
}
