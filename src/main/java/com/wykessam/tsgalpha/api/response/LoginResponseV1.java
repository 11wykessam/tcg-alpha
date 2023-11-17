package com.wykessam.tsgalpha.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author Samuel Wykes.
 * Represents the response for an attempt to login.
 */
@Getter
@Builder
public class LoginResponseV1 {

    private final String token;

    private final String refreshToken;

    @NonNull
    private final Boolean success;

}
