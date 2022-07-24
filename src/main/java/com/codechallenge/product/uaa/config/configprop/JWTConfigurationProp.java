package com.codechallenge.product.uaa.config.configprop;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("security.jwt")
public class JWTConfigurationProp {

    private String secret;

    private Integer jwtTTLInSeconds;
}
