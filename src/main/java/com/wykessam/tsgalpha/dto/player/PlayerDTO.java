package com.wykessam.tsgalpha.dto.player;

import com.wykessam.tsgalpha.persistence.entity.player.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author Samuel Wykes.
 * DTO used to represent {@link Player} objects.
 */
@Getter
@Builder
@RequiredArgsConstructor
public class PlayerDTO {

    @NotNull
    @NonNull
    private final UUID id;

}
