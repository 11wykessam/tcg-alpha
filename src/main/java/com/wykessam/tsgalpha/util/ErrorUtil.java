package com.wykessam.tsgalpha.util;

import com.wykessam.tsgalpha.api.response.ErrorAwareResponse;
import com.wykessam.tsgalpha.model.exception.ErrorInfo;
import org.springframework.http.HttpStatus;

/**
 * @author Samuel Wykes.
 * <p>
 * Utility class for creating error information.
 */
public class ErrorUtil {

    public static <T extends ErrorAwareResponse> T buildError(final Exception exception, final HttpStatus httpStatus,
                                                              final T response) {
        response.setErrorInfo(ErrorInfo.builder()
                .errorMessage(exception.getMessage())
                .httpStatus(httpStatus)
                .build()
        );
        return response;
    }

    public static <T extends ErrorAwareResponse> T buildError(final String errorMessage, final HttpStatus httpStatus,
                                                              final T response) {
        response.setErrorInfo(ErrorInfo.builder()
                .errorMessage(errorMessage)
                .httpStatus(httpStatus)
                .build()
        );
        return response;
    }

}
