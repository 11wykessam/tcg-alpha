package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.dto.card.CardDTO;
import com.wykessam.tsgalpha.exception.BoardNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.board.Board;
import com.wykessam.tsgalpha.persistence.entity.card.Card;
import com.wykessam.tsgalpha.persistence.repository.BoardDBRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Samuel Wykes.
 */
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    private static final UUID ID_ONE = UUID.randomUUID();
    private static final UUID ID_TWO = UUID.randomUUID();

    @Mock
    private BoardDBRepository boardDBRepository;

    @Mock
    private CardService cardService;

    @InjectMocks
    private BoardService boardService;

    @Test
    void getByIdSuccess(
            @Mock final Board board
    ) {
        when(this.boardDBRepository.findById(ID_ONE))
                .thenReturn(Mono.just(board));

        StepVerifier.create(this.boardService.getById(ID_ONE))
                .assertNext(response -> assertThat(response).isEqualTo(board))
                .verifyComplete();
    }

    @Test
    void getByIdNotFound() {
        when(this.boardDBRepository.findById(ID_ONE))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.boardService.getById(ID_ONE))
                .verifyErrorSatisfies(error -> assertThat(error instanceof BoardNotFoundException).isTrue());
    }

    @Test
    void toDTOSuccess(
            @Mock final Card cardOne,
            @Mock final Card cardTwo,
            @Mock final CardDTO cardDTOOne,
            @Mock final CardDTO cardDTOTwo
    ) {
        final Board board = Board.builder()
                .id(ID_ONE)
                .neutralAreaCardIds(Set.of(ID_ONE, ID_TWO))
                .homeAreaCardIds(Set.of(ID_ONE, ID_TWO))
                .graveCardIds(Set.of(ID_ONE, ID_TWO))
                .deckCardIds(new ArrayDeque<>(List.of(ID_ONE, ID_TWO)))
                .build();

        when(this.cardService.getById(ID_ONE))
                .thenReturn(Mono.just(cardOne));
        when(this.cardService.getById(ID_TWO))
                .thenReturn(Mono.just(cardTwo));
        when(this.cardService.toDTO(cardOne))
                .thenReturn(Mono.just(cardDTOOne));
        when(this.cardService.toDTO(cardTwo))
                .thenReturn(Mono.just(cardDTOTwo));

        StepVerifier.create(this.boardService.toDTO(board))
                .assertNext(boardDTO -> {
                    assertThat(boardDTO.getId()).isEqualTo(ID_ONE);
                    assertThat(boardDTO.getNeutralArea()).isEqualTo(Set.of(cardDTOOne, cardDTOTwo));
                    assertThat(boardDTO.getHomeArea()).isEqualTo(Set.of(cardDTOOne, cardDTOTwo));
                    assertThat(boardDTO.getGrave()).isEqualTo(Set.of(cardDTOOne, cardDTOTwo));
                    assertThat(boardDTO.getDeck().getFirst()).isEqualTo(cardDTOOne);
                    assertThat(boardDTO.getDeck().getLast()).isEqualTo(cardDTOTwo);
                })
                .verifyComplete();
    }

}