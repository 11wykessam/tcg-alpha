package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.dto.board.BoardDTO;
import com.wykessam.tsgalpha.exception.PlayerNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.board.Board;
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

    private static final UUID ID_ONE = UUID.randomUUID();
    private static final UUID ID_TWO = UUID.randomUUID();

    @Mock
    private PlayerDBRepository playerDBRepository;

    @Mock
    private BoardService boardService;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void getByIdSuccess(
            @Mock final Player player
    ) {
        when(this.playerDBRepository.findById(ID_ONE))
                .thenReturn(Mono.just(player));

        StepVerifier.create(this.playerService.getById(ID_ONE))
                .assertNext(response -> assertThat(response).isEqualTo(player))
                .verifyComplete();
    }

    @Test
    void getByIdNotFound() {
        when(this.playerDBRepository.findById(ID_ONE))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.playerService.getById(ID_ONE))
                .verifyErrorSatisfies(error -> assertThat(error instanceof PlayerNotFoundException).isTrue());
    }

    @Test
    void toDTOSuccess(
            @Mock final Board board,
            @Mock final BoardDTO boardDTO
    ) {
        final Player player = Player.builder()
                .id(ID_ONE)
                .boardId(ID_TWO)
                .build();

        when(this.boardService.getById(ID_TWO))
                .thenReturn(Mono.just(board));
        when(this.boardService.toDTO(board))
                .thenReturn(Mono.just(boardDTO));

        StepVerifier.create(this.playerService.toDTO(player))
                .assertNext(playerDTO -> {
                    assertThat(playerDTO.getId()).isEqualTo(ID_ONE);
                    assertThat(playerDTO.getBoard()).isEqualTo(boardDTO);
                })
                .verifyComplete();
    }

}