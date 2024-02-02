package com.wykessam.tsgalpha.controller;

import com.wykessam.tsgalpha.api.request.LoginRequestV1;
import com.wykessam.tsgalpha.api.request.SignUpRequestV1;
import com.wykessam.tsgalpha.api.response.LoginResponseV1;
import com.wykessam.tsgalpha.api.response.SignUpResponseV1;
import com.wykessam.tsgalpha.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    void login(
            @Mock final LoginRequestV1 request,
            @Mock final LoginResponseV1 expected
    ) {
        when(this.authService.login(request))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(this.authController.login(request))
                .assertNext(response -> {
                    assertThat(response.getBody()).isEqualTo(expected);
                    assertThat(response.getStatusCode()).isEqualTo(OK);
                })
                .verifyComplete();
    }

    @Test
    void signUp(
            @Mock final SignUpRequestV1 request,
            @Mock final SignUpResponseV1 expected
    ) {
        when(this.authService.signUp(request))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(this.authController.signUp(request))
                .assertNext(response -> {
                    assertThat(response.getStatusCode()).isEqualTo(OK);
                    assertThat(response.getBody()).isEqualTo(expected);
                })
                .verifyComplete();
    }

}