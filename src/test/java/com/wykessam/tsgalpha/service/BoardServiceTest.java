package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.exception.BoardNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.board.Board;
import com.wykessam.tsgalpha.persistence.repository.BoardDBRepository;
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
class BoardServiceTest {

    private static final UUID ID = UUID.randomUUID();

    @Mock
    private BoardDBRepository boardDBRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    void getByIdSuccess(
            @Mock final Board board
    ) {
        when(this.boardDBRepository.findById(ID))
                .thenReturn(Mono.just(board));

        StepVerifier.create(this.boardService.getById(ID))
                .assertNext(response -> assertThat(response).isEqualTo(board))
                .verifyComplete();
    }

    @Test
    void getByIdNotFound() {
        when(this.boardDBRepository.findById(ID))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.boardService.getById(ID))
                .verifyErrorSatisfies(error -> assertThat(error instanceof BoardNotFoundException).isTrue());
    }

}