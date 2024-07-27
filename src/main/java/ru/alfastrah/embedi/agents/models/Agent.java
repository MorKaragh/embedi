package ru.alfastrah.embedi.agents.models;

import java.util.UUID;

import ru.alfastrah.embedi.agents.dao.models.AgentStatus;

public class Agent {

    private UUID id;
    private String name;
    private AgentStatus status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgentStatus getStatus() {
        return status;
    }

    public void setStatus(AgentStatus status) {
        this.status = status;
    }

}
