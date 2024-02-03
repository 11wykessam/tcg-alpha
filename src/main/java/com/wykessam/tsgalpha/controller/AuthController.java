package com.wykessam.tsgalpha.controller;

import com.wykessam.tsgalpha.api.request.LoginRequestV1;
import com.wykessam.tsgalpha.api.request.SignUpRequestV1;
import com.wykessam.tsgalpha.api.response.LoginResponseV1;
import com.wykessam.tsgalpha.api.response.SignUpResponseV1;
import com.wykessam.tsgalpha.exception.InvalidCredentialsException;
import com.wykessam.tsgalpha.exception.UserExistsException;
import com.wykessam.tsgalpha.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * @author Samuel Wykes.
 * Controller used to log in users.
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Endpoint used by users to log in.
     * @param request {@link LoginRequestV1}.
     * @return {@link LoginResponseV1}.
     */
    @MessageMapping("auth.login.v1")
    public Mono<ResponseEntity<LoginResponseV1>> login(@Validated final LoginRequestV1 request) {
        return this.authService.login(request)
                .map(ResponseEntity::ok);
    }

    /**
     * Endpoint used by new users to sign-up.
     *
     * @param request {@link SignUpRequestV1}.
     * @return {@link SignUpResponseV1}.
     */
    @MessageMapping("auth.signUp.v2")
    public Mono<ResponseEntity<SignUpResponseV1>> signUp(@Validated final SignUpRequestV1 request) {
        return this.authService.signUp(request)
                .map(ResponseEntity::ok);
    }

    /**
     * @param exception {@link InvalidCredentialsException}.
     * @return {@link LoginResponseV1}.
     */
    @MessageExceptionHandler
    public ResponseEntity<LoginResponseV1> handle(final InvalidCredentialsException exception) {
        return ResponseEntity.status(FORBIDDEN).build();
    }

    /**
     * @param exception {@link UserExistsException}.
     * @return {@link SignUpResponseV1}.
     */
    @MessageExceptionHandler
    public ResponseEntity<SignUpResponseV1> handle(final UserExistsException exception) {
        return ResponseEntity.status(CONFLICT).build();
    }

}
