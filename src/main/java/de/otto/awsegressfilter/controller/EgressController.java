package de.otto.awsegressfilter.controller;

import de.otto.awsegressfilter.model.Region;
import de.otto.awsegressfilter.persistence.EgressIpRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class EgressController {

    private final EgressIpRepository egressIpRepository;

    public EgressController(EgressIpRepository egressIpRepository) {
        this.egressIpRepository = egressIpRepository;
    }

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> egressAdresses(@RequestParam(name = "region") Region region) {
        return egressIpRepository.findByRegion(region)
                .map(egressIp -> egressIp.concat(System.lineSeparator()));
    }
}
