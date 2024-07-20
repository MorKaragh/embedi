package ru.alfastrah.embedi.tick;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.tick.models.TickQuote;

@Service
public class TickCalcService {

    public Mono<TickQuote> calculate(TickQuote quote) {
       return null; 
    }
}

