package ru.alfastrah.embedi.tick.dao.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tick_insured_persons")
public class TickLinkerPersonRow {

    @Id
    private UUID id;
    @Column("quote_id")
    private UUID quoteId;
    @Column("person_id")
    private UUID personId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(UUID quoteId) {
        this.quoteId = quoteId;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID insurerId) {
        this.personId = insurerId;
    }

}
