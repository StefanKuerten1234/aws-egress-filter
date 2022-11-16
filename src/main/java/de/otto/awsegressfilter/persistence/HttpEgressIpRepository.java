package de.otto.awsegressfilter.persistence;

import de.otto.awsegressfilter.model.Region;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class HttpEgressIpRepository implements EgressIpRepository{

    @Override
    public Flux<String> findByRegion(Region region) {
        return null;
    }
}
