package ru.alfastrah.embedi.tick.dao.models;

public class AddressRecordTest {

    public static AddressRecord makeFake() {
        return makeFake(null);
    }

    public static AddressRecord makeFake(Integer pseed) {
        Integer seed = pseed != null ? pseed : 0;
        AddressRecord addressRec = new AddressRecord();
        addressRec.setFullAddressString(String.format("Street %s City Lol", String.valueOf(seed)));
        return addressRec;
    }


}

