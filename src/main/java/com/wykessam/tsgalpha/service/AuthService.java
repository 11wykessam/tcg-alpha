package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.api.request.LoginRequestV1;
import com.wykessam.tsgalpha.api.request.SignUpRequestV1;
import com.wykessam.tsgalpha.api.response.LoginResponseV1;
import com.wykessam.tsgalpha.api.response.SignUpResponseV1;
import com.wykessam.tsgalpha.persistence.entity.User;
import com.wykessam.tsgalpha.util.ErrorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author Samuel Wykes.
 * <p>
 * Service responsible for authenticating users.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    public static final String INVALID_CREDENTIALS = "Invalid Credentials";
    public static final String USERNAME_ALREADY_EXISTS = "Username Already Exists";

    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

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
                .switchIfEmpty(Mono.just(loginFailure()));
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
                                ? Mono.just(signUpFailure(request))
                                : this.userService.saveUser(User.builder()
                                        .username(request.getUsername())
                                        .password(this.passwordEncoder.encode(request.getPassword()))
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

    private static LoginResponseV1 loginFailure() {
        return ErrorUtil.buildError(INVALID_CREDENTIALS, UNAUTHORIZED,
                LoginResponseV1.builder().build()
        );
    }

    private static SignUpResponseV1 signupSuccess(final String token) {
        return SignUpResponseV1.builder()
                .token(token)
                .build();
    }

    private static SignUpResponseV1 signUpFailure(final SignUpRequestV1 request) {
        return ErrorUtil.buildError(USERNAME_ALREADY_EXISTS, CONFLICT,
                SignUpResponseV1.builder().build());
    }

}
