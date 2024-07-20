package ru.alfastrah.embedi.prodstream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.prodstream.models.TickCalcResult;

@Component
public class ProdStreamClient {

    private final WebClient streamWebClient;

    public ProdStreamClient(@Qualifier("streamWebClient") WebClient streamWebClient) {
        this.streamWebClient = streamWebClient;
    }

    public Mono<TickCalcResult> calculateTick() {
        return null;
    }

}
