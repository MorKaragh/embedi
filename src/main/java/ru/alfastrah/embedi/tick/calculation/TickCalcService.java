package ru.alfastrah.embedi.tick.calculation;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.agents.AgentsService;
import ru.alfastrah.embedi.prodstream.ProdStreamClient;
import ru.alfastrah.embedi.tick.calculation.models.TickProdStreamMapper;
import ru.alfastrah.embedi.tick.dao.TickDao;
import ru.alfastrah.embedi.tick.models.TickQuote;

@Service
public class TickCalcService {

    private final TickDao tickDao;
    private final ProdStreamClient prodStreamClient;
    private final AgentsService agentsService;

    public TickCalcService(TickDao tickDao, ProdStreamClient prodStreamClient, AgentsService agentsService) {
        this.tickDao = tickDao;
        this.prodStreamClient = prodStreamClient;
        this.agentsService = agentsService;
    }

    public Mono<TickQuote> calculate(TickQuote quote) {
        return agentsService.checkAgentIsActive(quote.getAgentId())
                .flatMap(x -> {
                    return prodStreamClient.calculateTick(TickProdStreamMapper.toCalcRequest(quote))
                            .map(response -> {
                                quote.setPremium(response.getPremium());
                                quote.setStreamCalcId(response.getCalcId());
                                return quote;
                            }).flatMap(calcedQuote -> {
                                return tickDao.saveQuote(calcedQuote);
                            });
                });
    }

}
