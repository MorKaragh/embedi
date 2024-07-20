package ru.alfastrah.embedi.tick.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.tick.TickCalcService;
import ru.alfastrah.embedi.tick.api.models.TickApiMapper;
import ru.alfastrah.embedi.tick.api.models.TickCalcReqeust;
import ru.alfastrah.embedi.tick.api.models.TickCalcResponse;

@RestController
@RequestMapping("/api/v1/tick")
public class TickController {

    private final TickCalcService calcService;

    public TickController(TickCalcService calcService) {
        this.calcService = calcService;
    }

    @PostMapping("/quotes")
    public Mono<TickCalcResponse> calculate(@RequestBody TickCalcReqeust request) {
        return calcService
        .calculate(null)
        .map(TickApiMapper::mapToResponse);
    }

}
