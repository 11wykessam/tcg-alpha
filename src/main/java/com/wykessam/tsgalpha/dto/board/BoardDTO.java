package com.wykessam.tsgalpha.dto.board;

import com.wykessam.tsgalpha.persistence.entity.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
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

}
