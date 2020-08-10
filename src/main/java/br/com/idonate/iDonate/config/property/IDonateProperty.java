package br.com.idonate.iDonate.config.property;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "idon")
public class IDonateProperty {

    @Getter
    private final Mail mail = new Mail();

    public @Data
    static class Mail {
        private String host;
        private Integer port;
        private String userName;
        private String password;
    }

}
