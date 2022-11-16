package de.otto.awsegressfilter.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.otto.awsegressfilter.model.EgressIp;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    void shouldParseFromJson() throws Exception {
        String input = """
                    {
                      "syncToken": "1668565390",
                      "createDate": "2022-11-16-02-23-10",
                      "prefixes": [
                        {
                          "ip_prefix": "3.2.34.0/26",
                          "region": "af-south-1",
                          "service": "AMAZON",
                          "network_border_group": "af-south-1"
                        }
                      ],
                      "ipv6_prefixes": [
                        {
                          "ipv6_prefix": "2600:1ff2:4000::/40",
                          "region": "us-west-2",
                          "service": "AMAZON",
                          "network_border_group": "us-west-2"
                        }
                      ]
                    }
                """;

        ApiResponse actual = new ObjectMapper().readerFor(ApiResponse.class).readValue(input);

        assertThat(actual.egressIps()).containsExactlyInAnyOrder(new EgressIp("3.2.34.0/26", "af-south-1"), new EgressIp("2600:1ff2:4000::/40", "us-west-2"));
    }

}