package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.api.request.LoginRequestV1;
import com.wykessam.tsgalpha.api.response.LoginResponseV1;
import com.wykessam.tsgalpha.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author Samuel Wykes.
 *
 * Service responsible for authenticating users.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    public static final String INVALID_CREDENTIALS = "Invalid Credentials";

    private final JwtService jwtService;
    private final UserService userService;

    /**
     * Perform a login request for a user.
     * @param request {@link LoginRequestV1}.
     * @return {@link LoginResponseV1}.
     */
    public Mono<LoginResponseV1> login(final LoginRequestV1 request) {
        return this.userService.getByUsername(request.getUsername())
                .filter(user -> BCrypt.checkpw(request.getPassword(), user.getPassword()))
                .flatMap(this.jwtService::generateToken)
                .map(AuthService::success)
                .switchIfEmpty(Mono.just(failure()));
    }

    private static LoginResponseV1 success(final String token) {
        return LoginResponseV1.builder()
                .token(token)
                .build();
    }

    private static LoginResponseV1 failure() {
        return ErrorUtil.buildError(INVALID_CREDENTIALS, UNAUTHORIZED,
                LoginResponseV1.builder()
                        .build()
        );
    }

}
