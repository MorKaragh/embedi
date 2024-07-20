package ru.alfastrah.embedi.agents.api;

import org.springframework.lang.NonNull;

public class CreateAgentRequest {

    @NonNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
