package ru.alfastrah.embedi.tick.api.models;

import java.util.List;
import java.util.UUID;

import ru.alfastrah.embedi.tick.models.Address;
import ru.alfastrah.embedi.tick.models.Person;
import ru.alfastrah.embedi.tick.models.TickQuote;

public class TickApiMapper {

    public static TickCalcResponse toTickCalcResponse(TickQuote quote) {
        TickCalcResponse response = new TickCalcResponse();
        response.setId(quote.getId());
        response.setPremium(quote.getPremium());
        return response;
    }

    public static TickQuote toTickQuote(TickCalcReqeust request, UUID agentId) {
        TickQuote quote = new TickQuote();
        quote.setStartDate(request.getStartDate());
        quote.setAgentId(agentId);
        quote.setEndDate(request.getEndDate());
        quote.setAddress(toAddress(request));
        quote.setInsurer(toPerson(request.getInsurer()));
        quote.setInsuredPersons(toPersons(request));
        return quote;
    }

    private static List<Person> toPersons(TickCalcReqeust request) {
        return request.getInsuredPersons().stream().map(p -> toPerson(p)).toList();
    }

    private static Person toPerson(PersonDto dto) {
        Person person = new Person();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setMiddleName(dto.getMiddleName());
        person.setBirthDate(dto.getBirthDate());
        return person;
    }

    private static Address toAddress(TickCalcReqeust request) {
        Address address = new Address();
        address.setFullAddressString(request.getAddress());
        return address;
    }

}
