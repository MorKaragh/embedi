package ru.alfastrah.embedi.tick.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TickQuoteTest {

    @Test
    public void creates(){
        TickQuote tickQuote = new TickQuote();
        assertNotNull(tickQuote);
    }
}
