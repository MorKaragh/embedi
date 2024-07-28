package ru.alfastrah.embedi.tick.api;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.tick.api.models.TickApiMapper;
import ru.alfastrah.embedi.tick.api.models.TickCalcReqeust;
import ru.alfastrah.embedi.tick.api.models.TickCalcResponse;
import ru.alfastrah.embedi.tick.calculation.TickCalcService;
import ru.alfastrah.embedi.tick.dao.TickDao;
import ru.alfastrah.embedi.tick.models.TickQuote;

@RestController
@RequestMapping("/api/v1/tick")
public class TickController {

    private final TickCalcService calcService;
    private final TickDao dao;

    public TickController(TickCalcService calcService, TickDao dao) {
        this.calcService = calcService;
        this.dao = dao;
    }

    @PostMapping("/quotes")
    public Mono<ResponseEntity<TickCalcResponse>> calculate(@RequestHeader("x-agent-id") UUID agentId,
            @Valid @RequestBody TickCalcReqeust request) {
        return calcService
                .calculate(TickApiMapper.toTickQuote(request, agentId))
                .map(q -> ResponseEntity.ok(TickApiMapper.toTickCalcResponse(q)));
    }

    @GetMapping("/quotes/{quoteId}")
    public Mono<TickQuote> testGetQuote(@PathVariable UUID quoteId) {
        return dao.findById(quoteId);
    }

}
