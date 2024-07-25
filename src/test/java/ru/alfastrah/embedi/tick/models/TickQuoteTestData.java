package ru.alfastrah.embedi.tick.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class TickQuoteTestData {

    public static TickQuote makeFake(Integer seed) {
        int aSeed = seed != null ? seed : 0;
        TickQuote tickQuote = new TickQuote();
        tickQuote.setAddress(AddressTestData.makeFake(aSeed));
        tickQuote.setEndDate(LocalDateTime.of(2022, 11, 14, 15, 50).plusDays(aSeed));
        tickQuote.setStartDate(LocalDateTime.of(2021, 11, 14, 15, 50).plusDays(aSeed));
        tickQuote.setPremium(BigDecimal.valueOf(100.50 + aSeed));
        tickQuote.setInsurer(PersonTestData.makeFake(aSeed));
        tickQuote.setInsuredPersons(
                IntStream.range(0, 5).mapToObj(i -> PersonTestData.makeFake(aSeed + i)).toList());
        return tickQuote;
    }

    public static TickQuote makeFake() {
        return makeFake(10);
    }

}
