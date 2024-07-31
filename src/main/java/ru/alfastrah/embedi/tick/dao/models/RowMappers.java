package ru.alfastrah.embedi.tick.dao.models;

import java.math.BigDecimal;

import ru.alfastrah.embedi.tick.models.Address;
import ru.alfastrah.embedi.tick.models.Person;
import ru.alfastrah.embedi.tick.models.TickQuote;

public class RowMappers {

    public static PersonRow toPersonRow(Person person) {
        PersonRow record = new PersonRow();
        record.setBirthDate(person.getBirthDate());
        record.setFirstName(person.getFirstName());
        record.setLastName(person.getLastName());
        record.setMiddleName(person.getMiddleName());
        record.setId(person.getId());
        return record;
    }

    public static Person toPerson(PersonRow row) {
        Person p = new Person();
        p.setId(row.getId());
        p.setFirstName(row.getFirstName());
        p.setMiddleName(row.getMiddleName());
        p.setLastName(row.getLastName());
        p.setBirthDate(row.getBirthDate());
        return p;
    }

    public static AddressRow toAddressRow(Address address) {
        AddressRow row = new AddressRow();
        row.setFullAddressString(address.getFullAddressString());
        return row;
    }

    public static TickQuoteRow toTickQuoteRow(TickQuote quote) {
        TickQuoteRow row = new TickQuoteRow();
        row.setAgentId(quote.getAgentId());
        row.setEndDate(quote.getEndDate());
        row.setStartDate(quote.getStartDate());
        row.setPremium(quote.getPremium() != null
                ? quote.getPremium().movePointRight(2).longValueExact()
                : null);
        row.setStreamCalcId(quote.getStreamCalcId());
        return row;
    }

    public static TickQuote toTickQuote(TickQuoteRow row) {
        TickQuote quote = new TickQuote();
        quote.setId(row.getId());
        quote.setStartDate(row.getStartDate());
        quote.setEndDate(row.getEndDate());
        quote.setAgentId(row.getAgentId());
        quote.setStreamCalcId(row.getStreamCalcId());
        quote.setInsurer(new Person().setId(row.getInsurerId()));
        quote.setAddress(new Address().setId(row.getAddressId()));
        quote.setPremium(row.getPremium() != null
                ? new BigDecimal(row.getPremium()).divide(new BigDecimal(100))
                : null);
        return quote;
    }

    public static Address toAddress(AddressRow row) {
        Address addr = new Address();
        addr.setId(row.getId());
        addr.setFullAddressString(row.getFullAddressString());
        return addr;
    }
}
