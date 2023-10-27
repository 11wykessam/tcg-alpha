package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.persistence.entity.User;
import com.wykessam.tsgalpha.persistence.repository.UserDBRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.wykessam.tsgalpha.persistence.entity.UserRole.ADMIN;

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
    private final PasswordEncoder passwordEncoder;

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

    @PostConstruct
    public void init() {
        this.userDBRepository
                .deleteAll()
                .then(this.userDBRepository.save(User.builder()
                                .username("user")
                        .password(this.passwordEncoder.encode("password"))
                                .role(ADMIN)
                                .build()))
                .subscribe(result -> log.info("User saved: " + result));
    }

}
