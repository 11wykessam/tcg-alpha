package com.wykessam.tsgalpha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Samuel Wykes.
 * Configuration related to securing RSocket connections.
 */
@Configuration
@EnableRSocketSecurity
public class RSocketSecurityConfiguration {

    @Value("${jwt.token.key}")
    private String jwtTokenKey;

    @Bean
    public PayloadSocketAcceptorInterceptor rsocketInterceptor(
            final RSocketSecurity rsocket,
            final ReactiveAuthenticationManager authenticationManager) {
        rsocket.authorizePayload(authorize ->
                        authorize
                                .route("auth.login.v1")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                                .anyExchange()
                                .permitAll()
                )
                .jwt(jwtSpec -> {
                    try {
                        jwtSpec.authenticationManager(authenticationManager);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return rsocket.build();
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(jwtTokenKey.getBytes(), mac.getAlgorithm());

        return NimbusReactiveJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
