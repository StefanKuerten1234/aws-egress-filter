package de.otto.awsegressfilter.persistence;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class HttpEgressIpRepository implements EgressIpRepository{

    @Override
    public Flux<String> findAll() {
        return null;
    }
}
