package com.wykessam.tsgalpha.persistence.entity.board;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Represents a player's board in the TCG.
 */
@Document(collection = "board")
@Getter
@Builder
@RequiredArgsConstructor
public class Board implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Builder.Default
    private final UUID id = UUID.randomUUID();

    @Builder.Default
    private final Set<UUID> neutralAreaCardIds = Set.of();

    @Builder.Default
    private final Set<UUID> homeAreaCardIds = Set.of();

    @Builder.Default
    private final Set<UUID> graveCardIds = Set.of();

    @Builder.Default
    private final Deque<UUID> deckCardIds = new ArrayDeque<>();

    @Builder.Default
    private final Deque<UUID> lifePileCardIds = new ArrayDeque<>();

    private final UUID championCardId;

    private final UUID battleFieldZoneCardId;

    private final UUID disruptionZoneOneCardId;

    private final UUID disruptionZoneTwoCardId;

    private final Integer portalCount;

    private final Integer engineCount;

}
