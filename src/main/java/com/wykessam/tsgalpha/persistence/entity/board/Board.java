package com.wykessam.tsgalpha.persistence.entity.board;

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

}
