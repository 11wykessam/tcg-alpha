package com.wykessam.tsgalpha;

import com.wykessam.tsgalpha.api.response.ErrorAwareResponse;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Samuel Wykes.
 */
public class ErrorUtil {

    public static void assertErrorHeader(
            final ErrorAwareResponse response,
            final String errorMessage, final HttpStatus httpStatus) {
        assertThat(response.hasError()).isTrue();
        assertThat(response.getErrorInfo()).isNotNull();
        assertThat(response.getErrorInfo().getErrorMessage()).isEqualTo(errorMessage);
        assertThat(response.getErrorInfo().getHttpStatus()).isEqualTo(httpStatus);
    }

}
