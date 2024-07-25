package ru.alfastrah.embedi.tick.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.alfastrah.embedi.tick.models.TickQuoteTestData;

@SpringBootTest
public class TickDaoTest {

    @Autowired
    private TickDao dao;

    @Test
    void testSaveQuote() {
        assertNotNull(dao);
    }

    @Test
    void testSaveQuote2() {
        dao.saveQuote(TickQuoteTestData.makeFake()).block();
    }

}
