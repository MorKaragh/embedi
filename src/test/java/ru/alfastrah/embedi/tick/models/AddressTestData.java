package ru.alfastrah.embedi.tick.models;

public class AddressTestData {

    public static Address makeFake(Integer seed) {
        int aSeed = seed != null ? seed : 0;
        Address address = new Address();
        address.setFullAddressString("full address string " + aSeed);
        return address;
    }

}

