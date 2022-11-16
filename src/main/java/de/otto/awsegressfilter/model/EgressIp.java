package de.otto.awsegressfilter.model;

public record EgressIp(String ip, String region) {

    @Override
    public String toString() {
        return ip;
    }
}
