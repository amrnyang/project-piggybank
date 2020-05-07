package org.pb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.service")
public class ApplicationConfiguration {

    private Boolean authenticateUser;
    private Boolean validateUser;
    private Authorise authorise;

    @Data
    static class Authorise {
        public Boolean authoriseUser;
    }
}
