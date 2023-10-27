package com.wykessam.tsgalpha.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 */
@RestController
@RequestMapping("/simple")
public class DumbController {

    @GetMapping("/message")
    public Mono<String> getMessage() {
        return Mono.just("HELLO WORLD");
    }

}
