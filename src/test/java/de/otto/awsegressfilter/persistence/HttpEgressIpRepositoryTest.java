package de.otto.awsegressfilter.persistence;

import de.otto.awsegressfilter.model.Region;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("entriesPerRegion")
    void shouldFetchAllIps(Region region, int numberOfIps) throws Exception{
        Path path = Paths.get("src/test/resources/api_response.json");
        String responseBody = Files.readString(path, Charset.defaultCharset());

        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(responseBody));

        Flux<String> actual = httpEgressIpRepository.findByRegion(region);
        StepVerifier.create(actual)
                .expectNextCount(numberOfIps)
                .verifyComplete();
    }

    private static Stream<Arguments> entriesPerRegion() {
        return Stream.of(
                Arguments.of(Region.ALL, 9_238),
                Arguments.of(Region.AF, 152),
                Arguments.of(Region.CA, 234),
                Arguments.of(Region.AP, 2_199),
                Arguments.of(Region.EU, 2_036),
                Arguments.of(Region.CN, 298),
                Arguments.of(Region.US, 3290),
                Arguments.of(Region.SA, 294)
        );
    }

}