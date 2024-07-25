package ru.alfastrah.embedi.tick.calculation.models;

import ru.alfastrah.embedi.prodstream.TickStreamCalcRequest;
import ru.alfastrah.embedi.tick.models.TickQuote;

public class TickProdStreamMapper {

    public static TickStreamCalcRequest toCalcRequest(TickQuote quote){
        TickStreamCalcRequest request = new TickStreamCalcRequest();
        request.setSomething(quote.toString());
        return request;
    }
    
}
