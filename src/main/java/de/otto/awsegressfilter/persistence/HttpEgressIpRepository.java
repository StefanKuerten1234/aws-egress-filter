package de.otto.awsegressfilter.persistence;

import de.otto.awsegressfilter.model.EgressIp;
import de.otto.awsegressfilter.model.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Repository
public class HttpEgressIpRepository implements EgressIpRepository {

    private final WebClient webClient;

    public HttpEgressIpRepository(@Value("${awsegressfilter.aws.api.url}") String apiUrl) {
        webClient = WebClient.builder()
                .baseUrl(apiUrl)
                // Allow for up to 10MB in memory store in order to process the large JSON
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();
    }

    @Override
    public Flux<String> findByRegion(Region region) {
        System.out.println("XXX" + region.toString());
        return webClient.get().retrieve().bodyToMono(ApiResponse.class)
                .map(ApiResponse::egressIps)
                .flatMapMany(Flux::fromIterable)
                .filter(egressIp -> region.equals(Region.ALL) || egressIp.region().toUpperCase().startsWith(region.toString()))
                .map(EgressIp::toString);
    }
}
