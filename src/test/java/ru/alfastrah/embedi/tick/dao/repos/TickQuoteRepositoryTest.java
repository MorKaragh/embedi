package ru.alfastrah.embedi.tick.dao.repos;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.alfastrah.embedi.tick.dao.models.TickQuoteRecord;
import ru.alfastrah.embedi.tick.dao.models.TickQuoteRecordTest;

public class TickQuoteRepositoryTest extends AbstractRepoContaineredTest{

    @Autowired
    private TickQuoteRepository tickQuoteRepository;

    @Test
    public void testSave() {
        TickQuoteRecord record = tickQuoteRepository.save(TickQuoteRecordTest.makeFake()).block();
        assertNotNull(record);
        assertNotNull(record.getId());

    }
}

