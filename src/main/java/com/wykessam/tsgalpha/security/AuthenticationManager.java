package com.wykessam.tsgalpha.security;

import com.wykessam.tsgalpha.service.JwtService;
import com.wykessam.tsgalpha.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 * Component responsible for managing authentications.
 */
@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    final JwtService jwtService;
    final UserService userService;

    @Override
    public Mono<Authentication> authenticate(final Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(auth -> auth instanceof BearerTokenAuthentication)
                .cast(BearerTokenAuthentication.class)
                .map(AbstractOAuth2TokenAuthenticationToken::getToken)
                .map(AbstractOAuth2Token::getTokenValue)
                .flatMap(this.jwtService::authenticateToken);
    }
}
