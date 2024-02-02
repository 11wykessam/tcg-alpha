package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.persistence.entity.User;
import com.wykessam.tsgalpha.persistence.repository.UserDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 *
 * Service responsible for processing users of the program.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserDBRepository userDBRepository;

    /**
     * Get the required service for Spring Security.
     * @return {@link ReactiveUserDetailsService}.
     */
    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return username -> this.userDBRepository.findFirstByUsername(username).cast(UserDetails.class)
                .doOnNext(user -> log.info(user.toString()));
    }

    /**
     * Get a user by their username.
     * @param username {@link String}.
     * @return {@link User}.
     */
    public Mono<User> getByUsername(final String username) {
        return this.userDBRepository.findFirstByUsername(username);
    }

    /**
     * Save a user to the database.
     *
     * @param user {@link User}.
     * @return {@link User}.
     */
    public Mono<User> saveUser(final User user) {
        return this.userDBRepository.save(user);
    }

}
