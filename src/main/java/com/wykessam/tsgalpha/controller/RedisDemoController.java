package com.wykessam.tsgalpha.controller;

import com.wykessam.tsgalpha.persistence.entity.User;
import com.wykessam.tsgalpha.persistence.repository.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 */

@RestController
public class RedisDemoController {

    final UserDBRepository repository;

    @Autowired
    public RedisDemoController(final UserDBRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/user")
    @CachePut(value = "user", key = "#user.id")
    public Mono<User> addNewUser(@RequestBody final User user) {
        return this.repository.save(user);
    }

    @GetMapping("/getUserById")
    @Cacheable(value = "user", key = "#id")
    public Mono<User> getUserById(@RequestParam final String id) {
        return this.repository.findById(id);
    }

    @PostMapping("/update")
    @CachePut(value = "user", key = "#user.id")
    public Mono<User> updateUser(@RequestBody final User user) {
        return this.repository.save(user);
    }

    @GetMapping("/delete")
    @CacheEvict(value = "user", key = "#id")
    public Mono<Void> deleteUserById(@RequestParam final String id) {
        return this.repository.deleteById(id);
    }
}