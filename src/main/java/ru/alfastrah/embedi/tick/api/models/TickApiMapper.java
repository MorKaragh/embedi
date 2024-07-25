package ru.alfastrah.embedi.tick.api.models;
import  ru.alfastrah.embedi.tick.models.TickQuote;

public class TickApiMapper {

    public static TickCalcResponse mapToResponse(TickQuote quote) {
        TickCalcResponse response = new TickCalcResponse();
        response.setId(quote.getId());
        response.setPremium(quote.getPremium());
        return null;
    }
}
