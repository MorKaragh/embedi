package ru.alfastrah.embedi.agents.dao.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import ru.alfastrah.embedi.agents.models.AgentStatus;


@Table(name = "agents")
public class AgentRow {

    @Id
    private UUID id;
    @Column("name")
    private String name;
    @Column("status")
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStatus(AgentStatus status) {
        this.status = status != null ? status.getValue() : null;
    }

}
