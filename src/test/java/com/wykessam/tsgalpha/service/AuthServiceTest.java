package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.api.request.LoginRequestV1;
import com.wykessam.tsgalpha.api.request.SignUpRequestV1;
import com.wykessam.tsgalpha.exception.InvalidCredentialsException;
import com.wykessam.tsgalpha.exception.UserExistsException;
import com.wykessam.tsgalpha.persistence.entity.user.User;
import com.wykessam.tsgalpha.persistence.entity.user.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.wykessam.tsgalpha.persistence.entity.user.UserRole.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private static final String USERNAME = "USER";
    private static final String PASSWORD = "PASSWORD";
    private static final String TOKEN = "TOKEN";
    private static final UserRole ROLE = ROLE_USER;


    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void loginSuccess() {
        final LoginRequestV1 request = createLoginRequest();

        final User extractedUser = User.builder()
                .username(USERNAME)
                .password(new BCryptPasswordEncoder().encode(PASSWORD))
                .role(ROLE)
                .build();

        when(this.userService.getByUsername(USERNAME))
                .thenReturn(Mono.just(extractedUser));
        when(this.jwtService.generateToken(extractedUser))
                .thenReturn(Mono.just(TOKEN));

        StepVerifier.create(this.authService.login(request))
                .assertNext(response -> assertThat(response.getToken()).isEqualTo(TOKEN))
                .verifyComplete();
    }

    @Test
    void loginFailureUserDoesNotExist() {
        final LoginRequestV1 request = createLoginRequest();

        when(this.userService.getByUsername(USERNAME))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.authService.login(request))
                .verifyErrorSatisfies(error -> assertThat(error instanceof InvalidCredentialsException).isTrue());
    }

    @Test
    void loginFailureInvalidPassword() {
        final LoginRequestV1 request = createLoginRequest();

        final User extractedUser = User.builder()
                .username(USERNAME)
                .password(new BCryptPasswordEncoder().encode(PASSWORD + "123"))
                .role(ROLE)
                .build();

        when(this.userService.getByUsername(USERNAME))
                .thenReturn(Mono.just(extractedUser));

        StepVerifier.create(this.authService.login(request))
                .verifyErrorSatisfies(error -> assertThat(error instanceof InvalidCredentialsException).isTrue());
    }

    @Test
    void signupSuccess() {
        final SignUpRequestV1 request = createSignUpRequest();

        final User user = User.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .role(ROLE)
                .build();

        when(this.userService.getByUsername(USERNAME))
                .thenReturn(Mono.empty());
        when(this.userService.saveUser(any()))
                .thenReturn(Mono.just(user));
        when(this.jwtService.generateToken(user))
                .thenReturn(Mono.just(TOKEN));

        StepVerifier.create(this.authService.signUp(request))
                .assertNext(response -> assertThat(response.getToken()).isEqualTo(TOKEN))
                .verifyComplete();
    }

    @Test
    void signupUsernameAlreadyExists() {
        final SignUpRequestV1 request = createSignUpRequest();

        final User user = User.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .role(ROLE)
                .build();

        when(this.userService.getByUsername(USERNAME))
                .thenReturn(Mono.just(user));

        StepVerifier.create(this.authService.signUp(request))
                .verifyErrorSatisfies(error -> assertThat(error instanceof UserExistsException).isTrue());
    }

    private static LoginRequestV1 createLoginRequest() {
        return LoginRequestV1.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

    private static SignUpRequestV1 createSignUpRequest() {
        return SignUpRequestV1.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

}