package ru.alfastrah.embedi.tick.api.models;

import java.util.List;
import java.util.UUID;

import ru.alfastrah.embedi.tick.models.Address;
import ru.alfastrah.embedi.tick.models.Person;
import ru.alfastrah.embedi.tick.models.TickQuote;

public class TickApiMapper {

    public static TickCalcResponse mapToResponse(TickQuote quote) {
        TickCalcResponse response = new TickCalcResponse();
        response.setId(quote.getId());
        response.setPremium(quote.getPremium());
        return null;
    }

    public static TickQuote mapToQuote(TickCalcReqeust request, UUID agentId) {
        TickQuote quote = new TickQuote();
        quote.setStartDate(request.getStartDate());
        quote.setAgentId(agentId);
        quote.setEndDate(request.getEndDate());
        quote.setAddress(mapAddress(request));
        quote.setInsurer(mapPerson(request.getInsurer()));
        quote.setInsuredPersons(mapInsuredPersons(request));
        return quote;
    }

    private static List<Person> mapInsuredPersons(TickCalcReqeust request) {
        return request.getInsuredPersons().stream().map(p -> mapPerson(p)).toList();
    }

    private static Person mapPerson(PersonDto dto) {
        Person person = new Person(); 
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setMiddleName(dto.getMiddleName());
        person.setBirthDate(dto.getBirthDate());
        return person;
    }

    private static Address mapAddress(TickCalcReqeust request) {
        Address address = new Address();
        address.setFullAddressString(request.getAddress());
        return address;
    }

}
