package ru.alfastrah.embedi.tick.dao.repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.tick.dao.models.AddressRecord;
import ru.alfastrah.embedi.tick.dao.models.AddressRecordTest;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository repository;

    @Test
    public void testSave() {
        Mono<AddressRecord> save = repository.save(AddressRecordTest.makeFake(1));
        save.block();
    }
}

