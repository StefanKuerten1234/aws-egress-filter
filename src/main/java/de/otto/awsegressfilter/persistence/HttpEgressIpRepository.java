package de.otto.awsegressfilter.persistence;

import de.otto.awsegressfilter.model.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Repository
public class HttpEgressIpRepository implements EgressIpRepository{

    private final WebClient webClient;

    public HttpEgressIpRepository(@Value("${awsegressfilter.aws.api.url}") String apiUrl) {
        webClient = WebClient.builder().baseUrl(apiUrl).build();
    }

    @Override
    public Flux<String> findByRegion(Region region) {
        return webClient.get().retrieve().bodyToFlux(String.class);
    }
}
