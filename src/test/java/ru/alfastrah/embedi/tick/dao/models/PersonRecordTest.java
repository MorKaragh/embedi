package ru.alfastrah.embedi.tick.dao.models;

import java.time.LocalDate;
import java.time.Month;

public class PersonRecordTest {

    public static PersonRecord makeFake() {
        return makeFake(null);
    }

    public static PersonRecord makeFake(Integer pseed) {
        Integer seed = pseed != null ? pseed : 0;
        PersonRecord personRec = new PersonRecord();
        personRec.setFirstName(String.format("FirstName", String.valueOf(seed)));
        personRec.setLastName(String.format("LastName", String.valueOf(seed)));
        personRec.setMiddleName(String.format("MiddleName", String.valueOf(seed)));
        personRec.setBirthDate(LocalDate.of(1987, Month.JANUARY, 14));
        return personRec;
    }

}
