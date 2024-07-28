package ru.alfastrah.embedi.tick.models;

import java.util.UUID;

public class Address {

    private UUID id;
    private String fullAddressString;

    public String getFullAddressString() {
        return fullAddressString;
    }

    public Address setFullAddressString(String fullAddressString) {
        this.fullAddressString = fullAddressString;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public Address setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", fullAddressString=" + fullAddressString + "]";
    }

}
