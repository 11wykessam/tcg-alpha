package com.wykessam.tsgalpha.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Samuel Wykes.
 *
 * Service responsible for handling creation and validation of JWTs.
 */
public class JwtService {

    private static final String ROLE_HEADER = "role";
    private static final Long EXPIRATION_MILLIS = 1000L * 30L;

    @Value("${jwt.token.key")
    private String jwtTokenKey;

    public Mono<String> generateToken(final UserDetails userDetails) {
        return Mono.just(Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(userDetails.getAuthorities().stream()
                        .collect(Collectors.toMap(authority -> ROLE_HEADER, GrantedAuthority::getAuthority)))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(secretKey())
                .compact());
    }

    private boolean isTokenExpired(final Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private Mono<Claims> extractClaims(final String token) {
        return Mono.just(Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload());
    }

    private SecretKey secretKey() {
        return new SecretKeySpec(
                Base64.getDecoder().decode(jwtTokenKey), 0, jwtTokenKey.length(), "AES");
    }

}
