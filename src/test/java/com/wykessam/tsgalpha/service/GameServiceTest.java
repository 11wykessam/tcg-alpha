package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.exception.GameNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.board.Game;
import com.wykessam.tsgalpha.persistence.repository.GameDBRepository;
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
class GameServiceTest {

    private static final UUID ID = UUID.randomUUID();

    @Mock
    private GameDBRepository gameDBRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    void getByIdSuccess(
            @Mock final Game game
    ) {
        when(this.gameDBRepository.findById(ID))
                .thenReturn(Mono.just(game));

        StepVerifier.create(this.gameService.getById(ID))
                .assertNext(response -> assertThat(response).isEqualTo(game))
                .verifyComplete();
    }

    @Test
    void getByIdNotFound() {
        when(this.gameDBRepository.findById(ID))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.gameService.getById(ID))
                .verifyErrorSatisfies(error -> assertThat(error instanceof GameNotFoundException).isTrue());
    }

}