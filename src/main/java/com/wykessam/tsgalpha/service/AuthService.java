package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.api.request.LoginRequestV1;
import com.wykessam.tsgalpha.api.request.SignUpRequestV1;
import com.wykessam.tsgalpha.api.response.LoginResponseV1;
import com.wykessam.tsgalpha.api.response.SignUpResponseV1;
import com.wykessam.tsgalpha.exception.InvalidCredentialsException;
import com.wykessam.tsgalpha.exception.UserExistsException;
import com.wykessam.tsgalpha.persistence.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.wykessam.tsgalpha.persistence.entity.user.UserRole.ROLE_USER;

/**
 * @author Samuel Wykes.
 * <p>
 * Service responsible for authenticating users.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;

    /**
     * Perform a login request for a user.
     *
     * @param request {@link LoginRequestV1}.
     * @return {@link LoginResponseV1}.
     */
    public Mono<LoginResponseV1> login(final LoginRequestV1 request) {
        return this.userService.getByUsername(request.getUsername())
                .filter(user -> BCrypt.checkpw(request.getPassword(), user.getPassword()))
                .flatMap(this.jwtService::generateToken)
                .map(AuthService::loginSuccess)
                .switchIfEmpty(Mono.error(new InvalidCredentialsException()));
    }

    /**
     * Perform a sign-up request for a new user.
     *
     * @param request {@link SignUpRequestV1}.
     * @return {@link SignUpResponseV1}.
     */
    public Mono<SignUpResponseV1> signUp(final SignUpRequestV1 request) {
        return this.userService.getByUsername(request.getUsername())
                .hasElement()
                .flatMap(usernameExists -> usernameExists
                        ? Mono.error(new UserExistsException(request.getUsername()))
                                : this.userService.saveUser(User.builder()
                                        .username(request.getUsername())
                                        .role(ROLE_USER)
                                        .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                                        .build()
                                )
                                .flatMap(this.jwtService::generateToken)
                                .map(AuthService::signupSuccess)
                );
    }

    private static LoginResponseV1 loginSuccess(final String token) {
        return LoginResponseV1.builder()
                .token(token)
                .build();
    }

    private static SignUpResponseV1 signupSuccess(final String token) {
        return SignUpResponseV1.builder()
                .token(token)
                .build();
    }

}
