package ru.alfastrah.embedi.agents.api.models;

import ru.alfastrah.embedi.agents.dao.models.AgentRow;
import ru.alfastrah.embedi.agents.models.Agent;
import ru.alfastrah.embedi.agents.models.AgentStatus;

public class AgentMapper {

    public static Agent toAgent(CreateAgentRequest request) {
        var agent = new Agent();
        agent.setName(request.getName());
        agent.setStatus(AgentStatus.ACTIVE);
        return agent;
    }

    public static AgentRow toAgentRow(Agent agent) {
        AgentRow row = new AgentRow();
        row.setName(agent.getName());
        row.setId(agent.getId());
        row.setStatus(agent.getStatus());
        return row;
    }

    public static Agent toAgent(AgentRow saved) {
        Agent agent = new Agent();
        agent.setId(saved.getId());
        agent.setName(saved.getName());
        agent.setStatus(AgentStatus.byValue(saved.getStatus()));
        return agent;
    }
}
