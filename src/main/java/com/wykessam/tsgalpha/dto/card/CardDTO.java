package com.wykessam.tsgalpha.dto.card;

import com.wykessam.tsgalpha.persistence.entity.card.Card;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author Samuel Wykes.
 * DTO used to represent {@link Card} objects.
 */
@Getter
@Builder
@RequiredArgsConstructor
public class CardDTO {

    @NotNull
    @NonNull
    private final UUID id;

}
