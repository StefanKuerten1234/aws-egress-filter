package de.otto.awsegressfilter.persistence;

import de.otto.awsegressfilter.model.Region;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class HttpEgressIpRepositoryTest {

    public static MockWebServer mockBackEnd;

    private HttpEgressIpRepository httpEgressIpRepository;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @BeforeEach
    void initialize() {
        String apiUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
        httpEgressIpRepository = new HttpEgressIpRepository(apiUrl);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void shouldFetchAllIps() throws Exception{
        Path path = Paths.get("src/test/resources/api_response.json");
        String responseBody = Files.readString(path, Charset.defaultCharset());

        mockBackEnd.enqueue(new MockResponse()
                .setBody(responseBody));

        Flux<String> actual = httpEgressIpRepository.findByRegion(Region.ALL);
        StepVerifier.create(actual)
                .expectNextCount(55_436)
                .verifyComplete();
    }
}