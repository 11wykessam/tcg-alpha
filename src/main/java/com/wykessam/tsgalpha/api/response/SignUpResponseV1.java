package com.wykessam.tsgalpha.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Samuel Wykes.
 * Represents the response for an attempt to sign-up.
 */
@Getter
@Builder
@Jacksonized
public class SignUpResponseV1 {

    private final String token;

}
