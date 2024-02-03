package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.dto.game.GameDTO;
import com.wykessam.tsgalpha.dto.game.GameDTO.GameDTOBuilder;
import com.wykessam.tsgalpha.exception.GameNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.game.Game;
import com.wykessam.tsgalpha.persistence.repository.GameDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Samuel Wykes.
 * Service responsible for handling logic involving {@link Game} objects.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GameService {

    private final GameDBRepository gameDBRepository;
    private final PlayerService playerService;

    /**
     * Get a game by its unique identifier.
     * @param id {@link UUID}.
     * @return {@link Game}.
     */
    public Mono<Game> getById(final UUID id) {
        return this.gameDBRepository.findById(id)
                .switchIfEmpty(Mono.error(new GameNotFoundException(id)));
    }

    /**
     * Convert game object to its DTO representation.
     *
     * @param game {@link Game}.
     * @return {@link GameDTO}.
     */
    public Mono<GameDTO> toDTO(final Game game) {
        return Mono.just(GameDTO.builder())
                .flatMap(builder -> this.enrichWithId(builder, game))
                .flatMap(builder -> this.enrichWithPlayers(builder, game))
                .map(GameDTOBuilder::build);
    }

    private Mono<GameDTOBuilder> enrichWithId(final GameDTOBuilder builder, final Game game) {
        return Mono.just(builder.id(game.getId()));
    }

    private Mono<GameDTOBuilder> enrichWithPlayers(final GameDTOBuilder builder, final Game game) {
        return Flux.fromIterable(game.getPlayerIds())
                .flatMap(this.playerService::getById)
                .flatMap(this.playerService::toDTO)
                .collect(Collectors.toSet())
                .map(builder::players);
    }

}
