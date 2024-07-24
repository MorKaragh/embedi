package ru.alfastrah.embedi.tick.models;

import java.time.LocalDate;

public class PersonTestData {

    public static Person makeFake(Integer seed) {
        int aSeed = seed != null? seed : 0;
        Person person = new Person();
        person.setFirstName("FirstName" + aSeed);
        person.setLastName("LastName" + aSeed);
        person.setMiddleName("MiddleName" + aSeed);
        person.setBirthDate(LocalDate.of(1987, 1, 14));
        return person;
    }
}

