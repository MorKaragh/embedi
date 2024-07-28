package ru.alfastrah.embedi.tick.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.util.Arrays;

public class TickQuoteTest {

    public static TickQuote makeFake(Integer seed) {
        TickQuote quote = new TickQuote();
        quote.setAddress(new Address().setFullAddressString("full address"));
        quote.setStartDate(LocalDateTime.of(2024, 11, 11, 15, 50));
        quote.setEndDate(LocalDateTime.of(2025, 11, 11, 15, 50));
        quote.setPremium(BigDecimal.valueOf(100.500));
        quote.setAgentId(null);
        quote.setInsurer(PersonTestData.makeFake(seed));
        quote.setInsuredPersons(List.of(PersonTestData.makeFake(seed +1), PersonTestData.makeFake(seed + 2)));
        return quote;
    }

}
