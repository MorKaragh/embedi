package ru.alfastrah.embedi.prodstream.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeansConfig {

    @Value("${prodstream.url}")
    private String prodstreamUrl;

    @Bean
    @Qualifier("streamWebClient")
    public WebClient streamWebClient() {
        return WebClient.builder().baseUrl(prodstreamUrl).build();
    }

}
