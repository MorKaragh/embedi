package ru.alfastrah.embedi.tick.calculation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.agents.AgentsService;
import ru.alfastrah.embedi.prodstream.ProdStreamClient;
import ru.alfastrah.embedi.prodstream.TickStreamCalcResult;
import ru.alfastrah.embedi.tick.dao.TickDao;
import ru.alfastrah.embedi.tick.models.TickQuoteTestData;

@ExtendWith(MockitoExtension.class)
public class TickCalcServiceTest {

    @InjectMocks
    private TickCalcService service;
    @Mock
    private ProdStreamClient prodStreamClient;
    @Mock
    private TickDao tickDao;
    @Mock
    private AgentsService agentsService;

    @Test
    void testCalculate() {
        Mockito.when(tickDao.saveQuote(Mockito.any())).thenReturn(Mono.just(TickQuoteTestData.makeFake()));
        Mockito.when(prodStreamClient.calculateTick(Mockito.any())).thenReturn(Mono.just(new TickStreamCalcResult()));
        Mockito.when(agentsService.checkAgentIsActive(Mockito.any())).thenReturn(Mono.just(true));
        service.calculate(TickQuoteTestData.makeFake()).block();
        Mockito.verify(prodStreamClient).calculateTick(Mockito.any());
        Mockito.verify(tickDao).saveQuote(Mockito.any());
    }

}

