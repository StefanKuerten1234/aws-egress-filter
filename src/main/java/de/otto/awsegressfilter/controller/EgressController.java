package de.otto.awsegressfilter.controller;

import de.otto.awsegressfilter.persistence.EgressIpRepository;
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

    @GetMapping
    public Flux<String> egressAdresses(@RequestParam String region) {
        return egressIpRepository.findAll()
                .map(egressIp -> egressIp.concat(System.lineSeparator()));
    }
}
