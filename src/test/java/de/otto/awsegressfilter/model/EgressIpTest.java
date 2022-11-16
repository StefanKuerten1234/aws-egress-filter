package de.otto.awsegressfilter.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EgressIpTest {

    @Test
    void shouldPrintOnlyIp() {
        Assertions.assertThat(new EgressIp("The IP", "any region").toString()).isEqualTo("The IP");
    }
}