package com.wykessam.tsgalpha.persistence.entity.game;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Represents a game of the TCG.
 */
@Document(collection = "game")
@Getter
@Builder
@RequiredArgsConstructor
public class Game implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Builder.Default
    private final UUID id = UUID.randomUUID();
    private final Set<UUID> playerIds;

}
