package com.wykessam.tsgalpha.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;

/**
 * @author Samuel Wykes.
 * Represents a request to login.
 */
@Getter
@Builder
@Jacksonized
public class LoginRequestV1 {

    @NotNull
    @NonNull
    private final String username;

    @NotNull
    @NonNull
    private final String password;

}
