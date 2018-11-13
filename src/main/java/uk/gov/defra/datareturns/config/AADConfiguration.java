package uk.gov.defra.datareturns.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.net.URL;

/**
 * Configuration options for Microsoft Azure active directory authenticating authority
 *
 * @author Sam Gardner-Dell
 */
@Configuration
@EnableConfigurationProperties
@ConditionalOnProperty(name = "dynamics.impl", havingValue = "dynamics")
@ConfigurationProperties(prefix = "active-directory")
@Getter
@Setter
@Validated
public class AADConfiguration {
    @NotNull
    private URL authority;

    @NotNull
    private String identityClientId;
}

