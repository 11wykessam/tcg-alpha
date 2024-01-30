package com.wykessam.tsgalpha.model.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

/**
 * @author Samuel Wykes.
 * Represents error info being returned in a response.
 */
@Getter
@Builder
@Jacksonized
public class ErrorInfo {

    private final HttpStatus httpStatus;

    private final String errorMessage;

}
