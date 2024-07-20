package ru.alfastrah.embedi.tick.dao.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "addresses")
public class AddressRecord {

    @Id
    private UUID id;

    @Column("full_address_string")
    private String fullAddressString;

    public String getFullAddressString() {
        return fullAddressString;
    }

    public void setFullAddressString(String addressFullString) {
        this.fullAddressString = addressFullString;
    }

}

