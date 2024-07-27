package ru.alfastrah.embedi.tick.api;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.tick.api.models.TickApiMapper;
import ru.alfastrah.embedi.tick.api.models.TickCalcReqeust;
import ru.alfastrah.embedi.tick.api.models.TickCalcResponse;
import ru.alfastrah.embedi.tick.calculation.TickCalcService;

@RestController
@RequestMapping("/api/v1/tick")
public class TickController {

    private final TickCalcService calcService;

    public TickController(TickCalcService calcService) {
        this.calcService = calcService;
    }

    @PostMapping("/quotes")
    public Mono<ResponseEntity<TickCalcResponse>> calculate(@RequestHeader("x-agent-id") UUID agentId,
            @RequestBody TickCalcReqeust request) {
        return calcService
                .calculate(TickApiMapper.toTickQuote(request, agentId))
                .map(q -> ResponseEntity.ok(TickApiMapper.toTickCalcResponse(q)));
    }

}
