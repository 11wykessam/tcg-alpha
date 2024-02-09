package com.wykessam.tsgalpha.persistence.entity.card;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * @author Samuel Wykes.
 * Represents a champion card in the TCG.
 */
@Document(collection = "card")
@TypeAlias(value = "creature")
@Getter
@SuperBuilder
public class CreatureCard extends Card {

    @NotNull
    @NonNull
    private final CreatureCardType originalType;

    @NotNull
    @NonNull
    @Builder.Default
    private final CreatureCardType type = this.originalType;

    @NotNull
    @NonNull
    private final Integer originalSize;

    @NotNull
    @NonNull
    @Builder.Default
    private final Integer size = this.originalSize;

    @NotNull
    @NonNull
    private final Integer originalPower;

    @NotNull
    @NonNull
    @Builder.Default
    private final Integer power = this.originalPower;

    @NotNull
    @NonNull
    private final Integer originalCritical;

    @NotNull
    @NonNull
    @Builder.Default
    private final Integer critical = this.originalCritical;

}
