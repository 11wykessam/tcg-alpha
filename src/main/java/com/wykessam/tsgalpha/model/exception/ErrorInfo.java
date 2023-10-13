package com.wykessam.tsgalpha.model.exception;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Samuel Wykes.
 * Represents error info being returned in a response.
 */
@Getter
@Builder
public class ErrorInfo {

    private final Integer errorCode;

    private final String errorMessage;

}
