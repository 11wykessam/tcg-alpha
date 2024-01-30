package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.api.request.LoginRequestV1;
import com.wykessam.tsgalpha.persistence.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private static final String USERNAME = "USER";
    private static final String PASSWORD = "PASSWORD";
    private static final String TOKEN = "TOKEN";
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
                .password(passwordEncoder.encode(PASSWORD))
                .build();

        when(this.userService.getByUsername(USERNAME))
                .thenReturn(Mono.just(extractedUser));
        when(this.jwtService.generateToken(extractedUser))
                .thenReturn(Mono.just(TOKEN));

        StepVerifier.create(this.authService.login(request))
                .assertNext(response -> {
                    assertThat(response.hasError()).isFalse();
                    assertThat(response.getToken()).isEqualTo(TOKEN);
                })
                .verifyComplete();
    }

    private static LoginRequestV1 createLoginRequest() {
        return LoginRequestV1.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

}