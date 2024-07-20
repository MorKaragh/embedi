package ru.alfastrah.embedi.agents.api;

import ru.alfastrah.embedi.agents.dao.AgentRecord;

public class AgentMapper {

    public static AgentRecord fromRequest(CreateAgentRequest request) {
        var agent = new AgentRecord();
        agent.setName(request.getName());
        return agent;
    }
}
