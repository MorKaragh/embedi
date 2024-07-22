package ru.alfastrah.embedi.tick.dao.repos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.alfastrah.embedi.tick.dao.models.AddressRecord;
import ru.alfastrah.embedi.tick.dao.models.AddressRecordTest;

public class AddressRepositoryTest extends AbstractRepoContaineredTest{

    @Autowired
    private AddressRepository repository;

    @Test
    public void testSave() {
        AddressRecord saved = repository.save(AddressRecordTest.makeFake(1)).block();
        assertNotNull(saved);
        AddressRecord found = repository.findById(saved.getId()).block();
        assertNotNull(found);
        assertEquals(saved.getFullAddressString(), found.getFullAddressString());
    }
}

