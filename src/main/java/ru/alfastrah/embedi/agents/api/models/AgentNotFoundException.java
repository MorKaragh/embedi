package ru.alfastrah.embedi.agents.api.models;

import ru.alfastrah.embedi.util.NotFoundException;

public class AgentNotFoundException extends NotFoundException {

    public AgentNotFoundException() {
        super("Агент не найден");
    }

}
