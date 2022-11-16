package de.otto.awsegressfilter.controller;

import de.otto.awsegressfilter.model.Region;
import de.otto.awsegressfilter.persistence.EgressIpRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.TEXT_PLAIN;

@SpringBootTest
@AutoConfigureWebTestClient
class EgressControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    EgressIpRepository egressIpRepository;

    @Test
    void shouldReturnBadRequest() {
        webClient.get().uri("/").exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void shouldReturnAllEgressIps() {
        when(egressIpRepository.findByRegion(Region.ALL)).thenReturn(Flux.fromIterable(List.of("127.0.0.1", "255.255.255.255")));
        webClient.get().uri("/?region=ALL").exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(TEXT_PLAIN)
                .expectBody(String.class).isEqualTo("""
                        127.0.0.1
                        255.255.255.255
                        """);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ALL", "EU", "US", "AP", "CN", "SA", "AF", "CA"})
    void shouldAllowValidRegion(String region) {
        when(egressIpRepository.findByRegion(any(Region.class))).thenReturn(Flux.fromIterable(List.of("127.0.0.1")));
        webClient.get().uri(String.format("/?region=%s", region)).exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldNotAllowInvalidRegion() {
        webClient.get().uri("/?region=INVALID").exchange()
                .expectStatus().isBadRequest();

    }
}