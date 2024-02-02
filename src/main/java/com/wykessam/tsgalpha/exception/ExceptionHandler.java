package com.wykessam.tsgalpha.exception;

import com.wykessam.tsgalpha.api.response.LoginResponseV1;
import com.wykessam.tsgalpha.api.response.SignUpResponseV1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Samuel Wykes.
 * Class responsible for handling exceptions.
 */
public class ExceptionHandler {

    /**
     * @param exception {@link UserNotFoundException}.
     * @return {@link LoginResponseV1}.
     */
    @MessageExceptionHandler
    public Mono<ResponseEntity<LoginResponseV1>> handle(final UserNotFoundException exception) {
        return Mono.just(LoginResponseV1.builder().build())
                .map(response -> ResponseEntity.status(NOT_FOUND).body(response));
    }

    /**
     * @param exception {@link UsernameExistsException}.
     * @return {@link SignUpResponseV1}.
     */
    @MessageExceptionHandler
    public Mono<ResponseEntity<SignUpResponseV1>> handle(final UsernameExistsException exception) {
        return Mono.just(SignUpResponseV1.builder().build())
                .map(response -> ResponseEntity.status(CONFLICT).body(response));
    }

    /**
     * @param exception {@link InvalidCredentialsException}.
     * @return {@link Void}.
     */
    @MessageExceptionHandler
    public Mono<ResponseEntity<Void>> handle(final InvalidCredentialsException exception) {
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
