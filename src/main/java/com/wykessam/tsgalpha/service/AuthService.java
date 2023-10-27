package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.api.request.LoginRequestV1;
import com.wykessam.tsgalpha.api.request.LoginResponseV1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 *
 * Service responsible for authenticating users.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final ReactiveAuthenticationManager reactiveAuthenticationManager;

    /**
     * Perform a login request for a user.
     * @param request {@link LoginRequestV1}.
     * @return {@link LoginResponseV1}.
     */
    public Mono<LoginResponseV1> login(final LoginRequestV1 request) {
        return reactiveAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        )
                .map(Authentication::getName)
                .flatMap(userService::getByUsername)
                .flatMap(jwtService::generateToken)
                .map(token -> LoginResponseV1.builder().token(token).build());
    }

}
