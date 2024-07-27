package ru.alfastrah.embedi.tick.api.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class TickCalcReqeust {

    private List<PersonDto> insuredPersons = new ArrayList<>();
    private PersonDto insurer;
    private String address;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;

    public List<PersonDto> getInsuredPersons() {
        return insuredPersons;
    }

    public void setInsuredPersons(List<PersonDto> insuredPersons) {
        this.insuredPersons = insuredPersons;
    }

    public PersonDto getInsurer() {
        return insurer;
    }

    public void setInsurer(PersonDto insurer) {
        this.insurer = insurer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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

}
