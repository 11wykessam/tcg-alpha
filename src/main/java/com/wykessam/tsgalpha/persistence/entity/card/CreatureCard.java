package com.wykessam.tsgalpha.persistence.entity.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class CreatureCard extends Card {

    @NotNull
    @NonNull
    private CreatureCardType originalType;

    @NotNull
    @NonNull
    private CreatureCardType type;

    @NotNull
    @NonNull
    private Integer originalSize;

    @NotNull
    @NonNull
    private Integer size;

    @NotNull
    @NonNull
    private Integer originalPower;

    @NotNull
    @NonNull
    private Integer power;

    @NotNull
    @NonNull
    private Integer originalCritical;

    @NotNull
    @NonNull
    private Integer critical;

}