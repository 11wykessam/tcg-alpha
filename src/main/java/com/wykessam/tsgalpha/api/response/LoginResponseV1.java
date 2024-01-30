package com.wykessam.tsgalpha.api.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Samuel Wykes.
 * Represents the response for an attempt to login.
 */
@Getter
@SuperBuilder
@Jacksonized
public class LoginResponseV1 extends ErrorAwareResponse {

    private final String token;

    private final String refreshToken;

}
