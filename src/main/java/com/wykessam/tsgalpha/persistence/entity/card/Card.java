package com.wykessam.tsgalpha.persistence.entity.card;

import com.wykessam.tsgalpha.persistence.entity.MutableAttribute;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Represents a card in the TCG.
 */
@Document(collection = "card")
@Getter
@SuperBuilder
@RequiredArgsConstructor
public abstract class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Builder.Default
    private final UUID id = UUID.randomUUID();

    @NotNull
    @NonNull
    private final MutableAttribute<String> name;

    @NotNull
    @NonNull
    private final String originalCardText;

    @NotNull
    @NonNull
    @Builder.Default
    private final String cardText = this.originalCardText;

    @NotNull
    @NonNull
    private final Integer originalSummoningRequirement;

    @NotNull
    @NonNull
    @Builder.Default
    private final Integer summoningRequirement;

}
