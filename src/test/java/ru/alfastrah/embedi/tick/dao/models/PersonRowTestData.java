package ru.alfastrah.embedi.tick.dao.models;

import java.time.LocalDate;
import java.time.Month;

public class PersonRowTestData {

    public static PersonRow makeFake() {
        return makeFake(null);
    }

    public static PersonRow makeFake(Integer pseed) {
        Integer seed = pseed != null ? pseed : 0;
        PersonRow personRec = new PersonRow();
        personRec.setFirstName(String.format("FirstName", String.valueOf(seed)));
        personRec.setLastName(String.format("LastName", String.valueOf(seed)));
        personRec.setMiddleName(String.format("MiddleName", String.valueOf(seed)));
        personRec.setBirthDate(LocalDate.of(1987, Month.JANUARY, 14));
        return personRec;
    }

}
