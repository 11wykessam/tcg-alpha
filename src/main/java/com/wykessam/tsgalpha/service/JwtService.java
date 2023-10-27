package com.wykessam.tsgalpha.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static org.springframework.security.core.authority.AuthorityUtils.NO_AUTHORITIES;
import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

/**
 * @author Samuel Wykes.
 *
 * Service responsible for handling creation and validation of JWTs.
 */
@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String ROLE_HEADER = "role";
    private static final Long EXPIRATION_MILLIS = 1000L * 60L * 60L;

    @Value("${jwt.token.key}")
    private String jwtTokenKey;

    /**
     * Generate a JWT based on given user details.
     * @param userDetails {@link UserDetails}.
     * @return {@link String}.
     */
    public Mono<String> generateToken(final UserDetails userDetails) {
        return Mono.just(Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(userDetails.getAuthorities().stream()
                        .collect(Collectors.toMap(authority -> ROLE_HEADER, GrantedAuthority::getAuthority)))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(this.secretKey())
                .compact());
    }

    /**
     * Authenticate a JWT. If successful, return the authentications associated with it.
     * @param token {@link String}.
     * @return {@link Authentication}.
     */
    public Mono<Authentication> authenticateToken(final String token) {
        return this.extractClaims(token)
                .switchIfEmpty(Mono.error(new JwtException("Invalid token")))
                .map(claims -> {
                    final Collection<? extends GrantedAuthority> authorities = this.extractAuthorities(claims);
                    return new UsernamePasswordAuthenticationToken(
                            new User(claims.getSubject(), "", authorities),
                            token,
                            authorities
                    );
                });
    }

    private Collection<? extends GrantedAuthority> extractAuthorities(final Claims claims) {
        final Object authoritiesClaim = claims.get(ROLE_HEADER);
        return authoritiesClaim == null
                ? NO_AUTHORITIES
                : commaSeparatedStringToAuthorityList(authoritiesClaim.toString());
    }

    private Mono<Claims> extractClaims(final String token) {
        return Mono.just(Jwts.parser()
                .verifyWith(this.secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload());
    }

    private SecretKey secretKey() {
        final String keyString = Base64.getEncoder()
                .encodeToString(this.jwtTokenKey.getBytes());
        return Keys.hmacShaKeyFor(keyString.getBytes(StandardCharsets.UTF_8));
    }

}
