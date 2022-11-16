package de.otto.awsegressfilter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class EgressControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void shouldReturnOk() {
        webClient.get().uri("/").exchange()
                .expectStatus().isOk();
    }
}