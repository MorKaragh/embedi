package ru.alfastrah.embedi.tick.dao.models;

import java.time.LocalDateTime;

public class TickQuoteRowTestData {

    public static TickQuoteRow makeFake() {
        TickQuoteRow tRecord = new TickQuoteRow();
        tRecord.setStartDate(LocalDateTime.of(2014, 11, 5, 23, 34));
        tRecord.setEndDate(LocalDateTime.of(2015, 11, 5, 23, 33));
        tRecord.setPremium(100.50);
        tRecord.setStreamQuoteId("ololo");
        return tRecord;
    }

}
