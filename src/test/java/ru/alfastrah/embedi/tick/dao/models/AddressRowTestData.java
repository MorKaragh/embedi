package ru.alfastrah.embedi.tick.dao.models;

public class AddressRowTestData {

    public static AddressRow makeFake() {
        return makeFake(null);
    }

    public static AddressRow makeFake(Integer pseed) {
        Integer seed = pseed != null ? pseed : 0;
        AddressRow addressRec = new AddressRow();
        addressRec.setFullAddressString(String.format("Street %s City Lol", String.valueOf(seed)));
        return addressRec;
    }


}

