package com.wykessam.tsgalpha.controller;

import com.wykessam.tsgalpha.api.request.LoginRequestV1;
import com.wykessam.tsgalpha.api.request.LoginResponseV1;
import com.wykessam.tsgalpha.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 * Controller used to log in users.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Mono<LoginResponseV1> login(@RequestBody final LoginRequestV1 request) {
        return authService.login(request);
    }

}
