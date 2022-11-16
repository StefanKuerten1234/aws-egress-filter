package de.otto.awsegressfilter.persistence;

import reactor.core.publisher.Flux;

public interface EgressIpRepository {

    Flux<String> findAll();

}
