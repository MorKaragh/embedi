package ru.alfastrah.embedi.agents.api.models;

import ru.alfastrah.embedi.agents.dao.models.AgentRecord;
import ru.alfastrah.embedi.agents.dao.models.AgentStatus;
import ru.alfastrah.embedi.agents.models.Agent;

public class AgentMapper {

    public static Agent fromRequest(CreateAgentRequest request) {
        var agent = new Agent();
        agent.setName(request.getName());
        agent.setStatus(AgentStatus.ACTIVE);
        return agent;
    }

    public static AgentRecord toAgentRow(Agent agent) {
        AgentRecord row = new AgentRecord();
        row.setName(agent.getName());
        row.setId(agent.getId());
        row.setStatus(agent.getStatus());
        return row;
    }

    public static Agent fromAgentRow(AgentRecord saved) {
        Agent agent = new Agent();
        agent.setId(saved.getId());
        agent.setName(saved.getName());
        agent.setStatus(AgentStatus.byValue(saved.getStatus()));
        return agent;
    }
}
