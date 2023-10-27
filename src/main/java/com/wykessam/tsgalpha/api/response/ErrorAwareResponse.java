package com.wykessam.tsgalpha.api.response;

import com.wykessam.tsgalpha.model.exception.ErrorInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @author Samuel Wykes.
 * Represents a response which can contain error information.
 */
@Getter
@SuperBuilder
@EqualsAndHashCode
public abstract class ErrorAwareResponse {

    private final ErrorInfo errorInfo;

    /**
     * Check if response has an error.
     * @return boolean.
     */
    public boolean hasError() {
        return this.errorInfo != null;
    }

}
