package ru.alfastrah.embedi.tick.models;

import java.time.LocalDate;
import java.util.UUID;

public class Person {

    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;

    public UUID getId() {
        return id;
    }

    public Person setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
                + ", birthDate=" + birthDate + "]";
    }

}
