package com.wykessam.tsgalpha.service;

import com.wykessam.tsgalpha.exception.PlayerNotFoundException;
import com.wykessam.tsgalpha.persistence.entity.player.Player;
import com.wykessam.tsgalpha.persistence.repository.PlayerDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Service responsible for handling logic involving {@link Player} objects.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerDBRepository playerDBRepository;

    /**
     * Get a player by its unique identifier.
     *
     * @param id {@link UUID}.
     * @return {@link Player}.
     */
    public Mono<Player> getById(final UUID id) {
        return this.playerDBRepository.findById(id)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(id)));
    }

}
