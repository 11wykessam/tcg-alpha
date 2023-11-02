package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.api.request.LoginRequestV1;
import com.wykessam.tsgalpha.api.response.LoginResponseV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 *
 * Service responsible for authenticating users.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Perform a login request for a user.
     * @param request {@link LoginRequestV1}.
     * @return {@link LoginResponseV1}.
     */
    public Mono<LoginResponseV1> login(final LoginRequestV1 request) {
        return this.userService.getByUsername(request.getUsername())
                .filter(user -> BCrypt.checkpw(request.getPassword(), user.getPassword()))
                .flatMap(this.jwtService::generateToken)
                .map(token -> LoginResponseV1.builder().token(token).build());
    }

}
