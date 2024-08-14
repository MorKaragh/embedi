package ru.alfastrah.embedi.tick.issuing;

import java.util.UUID;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.prodstream.ProdStreamClient;
import ru.alfastrah.embedi.prodstream.TickStreamIssueRequest;
import ru.alfastrah.embedi.tick.dao.TickDao;
import ru.alfastrah.embedi.tick.models.TickQuote;
import ru.alfastrah.embedi.util.NotFoundException;

@Service
public class TickIssueService {

    private final ProdStreamClient streamClient;
    private final TickDao dao;

    public TickIssueService(TickDao dao, ProdStreamClient streamClient) {
        this.streamClient = streamClient;
        this.dao = dao;
    }

    public Mono<TickQuote> issue(UUID tickQuoteId, UUID agentId) {
        dao.findById(tickQuoteId)
                .flatMap(q -> {
                    if (!agentId.equals(q.getAgentId())) {
                        return Mono.error(new NotFoundException("Котировка не найдена"));
                    }
                    return Mono.just(q).map(o -> {
                        streamClient.issueTick(new TickStreamIssueRequest().setCalcId(q.getStreamCalcId()));
                        return o;
                    });
                });
        return null;
    }

}
