package de.otto.awsegressfilter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class EgressController {

    @GetMapping
    public Flux<String> egressAdresses() {
        return Flux.empty();
    }
}
