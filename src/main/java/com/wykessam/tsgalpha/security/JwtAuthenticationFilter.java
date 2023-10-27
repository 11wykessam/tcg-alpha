package com.wykessam.tsgalpha.security;

import com.wykessam.tsgalpha.service.JwtService;
import io.jsonwebtoken.JwtException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author Samuel Wykes.
 * Filter used to validate JWT tokens.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private static final String HEADER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    /**
     * Filter requests to authenticate those with valid JWT tokens.
     *
     * @param exchange {@link ServerWebExchange}.
     * @param chain    {@link WebFilterChain}.
     * @return {@link Void}.
     */
    @Override
    @NonNull
    public Mono<Void> filter(@NonNull final ServerWebExchange exchange, @NonNull final WebFilterChain chain) {
        return this.extractToken(exchange.getRequest())
                .flatMap(this.jwtService::authenticateToken)
                .flatMap(authentication -> chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication)))
                .onErrorResume(error -> chain.filter(exchange));
    }

    private Mono<String> extractToken(final ServerHttpRequest request) {
        return Mono.justOrEmpty(request.getHeaders().getFirst(AUTHORIZATION))
                .filter(StringUtils::hasText)
                .filter(bearerToken -> bearerToken.startsWith(HEADER_PREFIX))
                .map(bearerToken -> bearerToken.substring(7))
                .switchIfEmpty(Mono.error(new JwtException("Authorization header does not contain bearer token")));
    }

}
