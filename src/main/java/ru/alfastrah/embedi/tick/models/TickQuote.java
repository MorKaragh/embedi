package ru.alfastrah.embedi.tick.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class TickQuote {

    private UUID id;
    private List<Person> insuredPersons;
    private Person insurer;
    private Address address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal premium;
    private String streamCalcId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Person> getInsuredPersons() {
        return insuredPersons;
    }

    public void setInsuredPersons(List<Person> insuredPersons) {
        this.insuredPersons = insuredPersons;
    }

    public Person getInsurer() {
        return insurer;
    }

    public void setInsurer(Person insurer) {
        this.insurer = insurer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public String getStreamCalcId() {
        return streamCalcId;
    }

    public void setStreamCalcId(String streamCalcId) {
        this.streamCalcId = streamCalcId;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

}
