package de.otto.awsegressfilter.persistence;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.otto.awsegressfilter.model.EgressIp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponse(
        List<EgressIp> egressIps
) {
    public ApiResponse {
        egressIps = new ArrayList<>();
    }

    @JsonProperty("prefixes")
    public void unpackV4Prefixes(List<Map<String, Object>> prefixes) {
        prefixes.forEach(ip -> egressIps.add(new EgressIp((String) ip.get("ip_prefix"), (String) ip.get("region"))));
    }

    @JsonProperty("ipv6_prefixes")
    public void unpackV6Prefixes(List<Map<String, Object>> prefixes) {
        prefixes.forEach(ip -> egressIps.add(new EgressIp((String) ip.get("ipv6_prefix"), (String) ip.get("region"))));
    }
}
