package com.wykessam.tsgalpha.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Samuel Wykes.
 * Represents the response for an attempt to login.
 */
@Getter
@Builder
@Jacksonized
public class LoginResponseV1 {

    private final String token;

    private final String refreshToken;

}
