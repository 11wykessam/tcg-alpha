package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.exception.GameNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.board.Game;
import com.wykessam.tsgalpha.persistence.repository.GameDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Service responsible for handling logic involving {@link Game} objects.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GameService {

    private final GameDBRepository gameDBRepository;

    /**
     * Get a game by its unique identifier.
     * @param id {@link UUID}.
     * @return {@link Game}.
     */
    public Mono<Game> getById(final UUID id) {
        return this.gameDBRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException(id)));
    }

}
