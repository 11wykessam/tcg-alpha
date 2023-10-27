package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.persistence.entity.User;
import com.wykessam.tsgalpha.persistence.entity.UserRole;
import com.wykessam.tsgalpha.persistence.repository.UserDBRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        return username -> userDBRepository.findFirstByUsername(username).cast(UserDetails.class)
                .doOnNext(user -> log.info(user.toString()));
    }

    @PostConstruct
    public void init() {
        userDBRepository
                .deleteAll()
                .then(userDBRepository.save(User.builder()
                                .username("user")
                                .password(passwordEncoder.encode("password"))
                                .role(ADMIN)
                                .build()))
                .subscribe(result -> log.info("User saved: " + result));
    }

}
