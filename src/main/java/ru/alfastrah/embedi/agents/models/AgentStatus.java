package ru.alfastrah.embedi.agents.models;

import java.util.Arrays;

public enum AgentStatus {

    ACTIVE(1),
    INACTIVE(0);

    private final int value;

    AgentStatus(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public static AgentStatus byValue(Integer value) {
        return Arrays.stream(AgentStatus.values())
        .filter(a -> a.getValue() == value)
        .findFirst()
        .orElse(null);
    }

}
