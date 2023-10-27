package com.wykessam.tsgalpha.security;

import com.wykessam.tsgalpha.service.JwtService;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    private static final String BEARER_TOKEN = "Bearer oasodiaspdiasodsa";

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void filterSuccess(
            @Mock final ServerWebExchange exchange,
            @Mock final WebFilterChain filterChain,
            @Mock final ServerHttpRequest request,
            @Mock final HttpHeaders httpHeaders,
            @Mock final Authentication authentication
            ) {
        when(exchange.getRequest()).thenReturn(request);
        when(request.getHeaders()).thenReturn(httpHeaders);
        when(httpHeaders.getFirst(AUTHORIZATION)).thenReturn(BEARER_TOKEN);
        when(this.jwtService.authenticateToken(any())).thenReturn(Mono.just(authentication));
        when(filterChain.filter(exchange)).thenReturn(Mono.empty());

        StepVerifier.create(this.jwtAuthenticationFilter.filter(exchange, filterChain))
                .verifyComplete();

        verify(filterChain, times(1)).filter(exchange);
    }

    @Test
    void filterFailure(
            @Mock final ServerWebExchange exchange,
            @Mock final WebFilterChain filterChain,
            @Mock final ServerHttpRequest request,
            @Mock final HttpHeaders httpHeaders
    ) {
        when(exchange.getRequest()).thenReturn(request);
        when(request.getHeaders()).thenReturn(httpHeaders);
        when(httpHeaders.getFirst(AUTHORIZATION)).thenReturn(BEARER_TOKEN);
        when(this.jwtService.authenticateToken(any())).thenReturn(Mono.error(new JwtException("I am a teapot")));
        when(filterChain.filter(exchange)).thenReturn(Mono.empty());

        StepVerifier.create(this.jwtAuthenticationFilter.filter(exchange, filterChain))
                .verifyComplete();

        verify(filterChain, times(1)).filter(exchange);
    }

}