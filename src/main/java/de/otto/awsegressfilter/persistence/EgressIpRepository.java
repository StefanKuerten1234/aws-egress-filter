package de.otto.awsegressfilter.persistence;

import de.otto.awsegressfilter.model.Region;
import reactor.core.publisher.Flux;

public interface EgressIpRepository {

    Flux<String> findByRegion(Region region);

}
