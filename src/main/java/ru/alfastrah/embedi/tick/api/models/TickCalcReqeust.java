package ru.alfastrah.embedi.tick.api.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public class TickCalcReqeust {

    private List<PersonDto> insuredPersons = new ArrayList<>();
    @NotNull(message = "Страхователь должен быть указан")
    private PersonDto insurer;
    @NotNull(message = "Адрес должен быть указан")
    private String address;
    @NotNull(message = "Дата начала должна быть указана")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    @NotNull(message = "Дата окончания должна быть указана")
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
