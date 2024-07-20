package ru.alfastrah.embedi.agents.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import ru.alfastrah.embedi.agents.AgentsService;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentsController {

    private final AgentsService service;

    public AgentsController(AgentsService service) {
        this.service = service;
    }

    @PostMapping("/")
    public Mono<CreateAgentResponse> createAgent(@RequestBody CreateAgentRequest request) {
        return service
                .saveAgent(AgentMapper.fromRequest(request))
                .map(a -> new CreateAgentResponse());
    }

}
