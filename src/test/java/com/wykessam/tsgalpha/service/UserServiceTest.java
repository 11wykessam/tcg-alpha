package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.persistence.entity.user.User;
import com.wykessam.tsgalpha.persistence.repository.UserDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Mock
    private UserDBRepository userDBRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getByUsernameTest() {
        final User extractedUser = User.builder()
                .username(USERNAME)
                .password(passwordEncoder.encode(PASSWORD))
                .build();

        when(this.userDBRepository.findFirstByUsername(USERNAME))
                .thenReturn(Mono.just(extractedUser));

        StepVerifier.create(this.userService.getByUsername(USERNAME))
                .assertNext(user -> {
                    assertThat(user).isEqualTo(extractedUser);
                })
                .verifyComplete();
    }

    @Test
    void saveUserTest() {
        final User userToSave = User.builder()
                .username(USERNAME)
                .password(passwordEncoder.encode(PASSWORD))
                .build();

        when(this.userDBRepository.save(userToSave))
                .thenReturn(Mono.just(userToSave));

        StepVerifier.create(this.userService.saveUser(userToSave))
                .assertNext(user -> {
                    assertThat(user).isEqualTo(userToSave);
                })
                .verifyComplete();
    }

}