package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.dto.board.BoardDTO;
import com.wykessam.tsgalpha.dto.board.BoardDTO.BoardDTOBuilder;
import com.wykessam.tsgalpha.exception.BoardNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.board.Board;
import com.wykessam.tsgalpha.persistence.repository.BoardDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Service responsible for handling logic involving {@link Board} objects.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardDBRepository boardDBRepository;

    /**
     * Get a player by its unique identifier.
     *
     * @param id {@link UUID}.
     * @return {@link Board}.
     */
    public Mono<Board> getById(final UUID id) {
        return this.boardDBRepository.findById(id)
                .switchIfEmpty(Mono.error(new BoardNotFoundException(id)));
    }

    /**
     * Convert board object to its DTO representation.
     *
     * @param board {@link Board}.
     * @return {@link BoardDTO}.
     */
    public Mono<BoardDTO> toDTO(final Board board) {
        return Mono.just(BoardDTO.builder())
                .flatMap(builder -> this.enrichWithId(builder, board))
                .map(BoardDTOBuilder::build);

    }

    private Mono<BoardDTOBuilder> enrichWithId(final BoardDTOBuilder builder, final Board board) {
        return Mono.just(builder.id(board.getId()));
    }

}