package ru.alfastrah.embedi.tick.dao.models;

import ru.alfastrah.embedi.tick.models.Address;
import ru.alfastrah.embedi.tick.models.Person;
import ru.alfastrah.embedi.tick.models.TickQuote;

public class RowMappers {

    public static PersonRow personRow(Person person) {
        PersonRow record = new PersonRow();
        record.setBirthDate(person.getBirthDate());
        record.setFirstName(person.getFirstName());
        record.setLastName(person.getLastName());
        record.setMiddleName(person.getMiddleName());
        record.setId(person.getId());
        return record;
    }
    
    public static AddressRow addressRow(Address address) {
        AddressRow row = new AddressRow();
        row.setFullAddressString(address.getFullAddressString());
        return row;
    }

    public static TickQuoteRow tickQuoteRow(TickQuote quote) {
        TickQuoteRow row = new TickQuoteRow();
        row.setEndDate(quote.getEndDate());
        row.setStartDate(quote.getStartDate());
        row.setPremium(quote.getPremium());
        return row;
    }
}
