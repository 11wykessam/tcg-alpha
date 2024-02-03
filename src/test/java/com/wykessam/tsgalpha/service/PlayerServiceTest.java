package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.exception.PlayerNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.player.Player;
import com.wykessam.tsgalpha.persistence.repository.PlayerDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    private static final UUID ID = UUID.randomUUID();

    @Mock
    private PlayerDBRepository playerDBRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void getByIdSuccess(
            @Mock final Player player
    ) {
        when(this.playerDBRepository.findById(ID))
                .thenReturn(Mono.just(player));

        StepVerifier.create(this.playerService.getById(ID))
                .assertNext(response -> assertThat(response).isEqualTo(player))
                .verifyComplete();
    }

    @Test
    void getByIdNotFound() {
        when(this.playerDBRepository.findById(ID))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.playerService.getById(ID))
                .verifyErrorSatisfies(error -> assertThat(error instanceof PlayerNotFoundException).isTrue());
    }

}