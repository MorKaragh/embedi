package ru.alfastrah.embedi.tick.dao.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tick_quotes")
public class TickQuoteRow {

    @Id
    private UUID id;
    @Column("insurer_id")
    private UUID insurerId;
    @Column("address_id")
    private UUID address_id;
    @Column("start_date")
    private LocalDateTime startDate;
    @Column("end_date")
    private LocalDateTime endDate;
    @Column("premium")
    private Long premium;
    @Column("stream_quote_id")
    private String streamCalcId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(UUID insurerId) {
        this.insurerId = insurerId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public UUID getAddress_id() {
        return address_id;
    }

    public void setAddress_id(UUID address_id) {
        this.address_id = address_id;
    }

    public Long getPremium() {
        return premium;
    }

    public void setPremium(Long premium) {
        this.premium = premium;
    }

    public String getStreamCalcId() {
        return streamCalcId;
    }

    public void setStreamCalcId(String streamQuoteId) {
        this.streamCalcId = streamQuoteId;
    }

}
