package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.dto.player.PlayerDTO;
import com.wykessam.tsgalpha.exception.GameNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.game.Game;
import com.wykessam.tsgalpha.persistence.entity.player.Player;
import com.wykessam.tsgalpha.persistence.repository.GameDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    private static final UUID ID_ONE = UUID.randomUUID();
    private static final UUID ID_TWO = UUID.randomUUID();

    @Mock
    private GameDBRepository gameDBRepository;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private GameService gameService;

    @Test
    void getByIdSuccess(
            @Mock final Game game
    ) {
        when(this.gameDBRepository.findById(ID_ONE))
                .thenReturn(Mono.just(game));

        StepVerifier.create(this.gameService.getById(ID_ONE))
                .assertNext(response -> assertThat(response).isEqualTo(game))
                .verifyComplete();
    }

    @Test
    void getByIdNotFound() {
        when(this.gameDBRepository.findById(ID_ONE))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.gameService.getById(ID_ONE))
                .verifyErrorSatisfies(error -> assertThat(error instanceof GameNotFoundException).isTrue());
    }

    @Test
    void toDTOSuccess(
            @Mock final Player playerOne,
            @Mock final Player playerTwo,
            @Mock final PlayerDTO playerDTOOne,
            @Mock final PlayerDTO playerDTOTwo
    ) {
        final Game game = Game.builder()
                .id(ID_ONE)
                .playerIds(Set.of(ID_ONE, ID_TWO))
                .build();

        when(this.playerService.getById(ID_ONE))
                .thenReturn(Mono.just(playerOne));
        when(this.playerService.getById(ID_TWO))
                .thenReturn(Mono.just(playerTwo));
        when(this.playerService.toDTO(playerOne))
                .thenReturn(Mono.just(playerDTOOne));
        when(this.playerService.toDTO(playerTwo))
                .thenReturn(Mono.just(playerDTOTwo));

        StepVerifier.create(this.gameService.toDTO(game))
                .assertNext(gameDTO -> {
                    assertThat(gameDTO.getId()).isEqualTo(ID_ONE);
                    assertThat(gameDTO.getPlayers()).isEqualTo(Set.of(playerDTOOne, playerDTOTwo));
                })
                .verifyComplete();
    }

}