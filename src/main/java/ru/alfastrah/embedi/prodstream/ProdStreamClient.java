package ru.alfastrah.embedi.prodstream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class ProdStreamClient {

    private final WebClient streamWebClient;

    public ProdStreamClient(@Qualifier("streamWebClient") WebClient streamWebClient) {
        this.streamWebClient = streamWebClient;
    }

    public Mono<TickStreamCalcResult> calculateTick(TickStreamCalcRequest request) {
        return streamWebClient
                .post()
                .uri("/calc")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(TickStreamCalcResult.class);
    }

    public Mono<TickStreamIssueResult> issueTick(TickStreamIssueRequest request) {
        return streamWebClient
                .post()
                .uri("/issue")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(TickStreamIssueResult.class);
    }

}
