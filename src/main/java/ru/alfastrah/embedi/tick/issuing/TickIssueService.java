package ru.alfastrah.embedi.tick.issuing;

import java.util.UUID;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.tick.dao.TickDao;
import ru.alfastrah.embedi.tick.models.TickQuote;

@Service
public class TickIssueService {

    private final TickDao dao;

    public TickIssueService(TickDao dao) {
        this.dao = dao;
    }

    public Mono<TickQuote> issue(UUID tickQuoteId, UUID agentId){
        return null;
    }
    
}
