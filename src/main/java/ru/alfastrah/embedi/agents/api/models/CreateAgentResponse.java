package ru.alfastrah.embedi.agents.api.models;

import java.util.UUID;

public class CreateAgentResponse {

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CreateAgentResponse(UUID id) {
        this.id = id;
    }

}

