package com.wykessam.tsgalpha.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Samuel Wykes.
 * Represents the response for an attempt to login.
 */
@Getter
@Builder
@AllArgsConstructor
public class LoginResponseV1 {

    private final String token;

}
