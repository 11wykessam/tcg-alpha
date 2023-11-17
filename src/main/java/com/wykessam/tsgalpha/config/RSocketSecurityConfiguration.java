package com.wykessam.tsgalpha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;

/**
 * @author Samuel Wykes.
 * Configuration related to securing RSocket connections.
 */
@Configuration
@EnableRSocketSecurity
public class RSocketSecurityConfiguration {

    /**
     * Configure security for RSocket connections.
     *
     * @param rsocket               {@link RSocketSecurity}.
     * @param authenticationManager {@link ReactiveAuthenticationManager}.
     * @return {@link PayloadSocketAcceptorInterceptor}.
     */
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
                .jwt(jwtSpec -> jwtSpec.authenticationManager(authenticationManager));
        return rsocket.build();
    }

    /**
     * Determines the password encoder used by the rest of the application.
     * @return {@link PasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
