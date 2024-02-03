package com.wykessam.tsgalpha.dto.game;

import com.wykessam.tsgalpha.dto.player.PlayerDTO;
import com.wykessam.tsgalpha.persistence.entity.game.Game;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

/**
 * @author Samuel Wykes.
 * DTO used to represent {@link Game} objects.
 */
@Getter
@Builder
@RequiredArgsConstructor
public class GameDTO {

    @NotNull
    @NonNull
    private final UUID id;

    @NotNull
    @NonNull
    private final Set<PlayerDTO> players;

}
