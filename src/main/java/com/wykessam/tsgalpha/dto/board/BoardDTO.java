package com.wykessam.tsgalpha.dto.board;

import com.wykessam.tsgalpha.dto.card.CardDTO;
import com.wykessam.tsgalpha.persistence.entity.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Deque;
import java.util.Set;
import java.util.UUID;

/**
 * @author Samuel Wykes.
 * DTO used to represent {@link Board} objects.
 */
@Getter
@Builder
@RequiredArgsConstructor
public class BoardDTO {

    @NotNull
    @NonNull
    private final UUID id;

    @NotNull
    @NonNull
    private final Set<CardDTO> neutralArea;

    @NotNull
    @NonNull
    private final Set<CardDTO> homeArea;

    @NotNull
    @NonNull
    private final Set<CardDTO> grave;

    @NotNull
    @NonNull
    private final Deque<CardDTO> deck;

}
