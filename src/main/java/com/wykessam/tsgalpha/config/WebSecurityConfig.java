package com.wykessam.tsgalpha.config;

import com.wykessam.tsgalpha.security.JwtAuthenticationFilter;
import com.wykessam.tsgalpha.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.HTTP_BASIC;

/**
 * @author Samuel Wykes.
 */
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    /**
     * Configuration for the security filter chain.
     * @param http {@link ServerHttpSecurity}.
     * @param authenticationManager {@link ReactiveAuthenticationManager}.
     * @param jwtService {@link JwtService}.
     * @return {@link SecurityWebFilterChain}.
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            final ServerHttpSecurity http,
            final ReactiveAuthenticationManager authenticationManager,
            final JwtService jwtService) {
        http
                .csrf(CsrfSpec::disable)
                .httpBasic(HttpBasicSpec::disable)
                .authenticationManager(authenticationManager)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**").permitAll()
                        .anyExchange().authenticated()
                )
                .addFilterAt(new JwtAuthenticationFilter(jwtService), HTTP_BASIC);
        return http.build();
    }

    /**
     * Bean representing the authentication manager used by the application.
     *
     * @param userDetailsService {@link ReactiveUserDetailsService}.
     * @param passwordEncoder    {@link PasswordEncoder}.
     * @return {@link ReactiveAuthenticationManager}.
     */
    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(
            final ReactiveUserDetailsService userDetailsService, final PasswordEncoder passwordEncoder) {
        final UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }

    /**
     * Bean representing the password encoder used by the application.
     * @return {@link PasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
