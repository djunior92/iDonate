package br.com.idonate.iDonate.apicielo.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "apicielo")
public class ApiCieloProperty {

    @Getter
    private final Cielo cielo = new Cielo();

    public @Data
    static class Cielo {
        private String merchantId;
        private String merchantKey;
        private String apiUrlQuery;
        private String apiUrl;
    }

}
