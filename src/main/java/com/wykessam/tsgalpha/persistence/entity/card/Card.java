package com.wykessam.tsgalpha.persistence.entity.card;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @NonNull
    private UUID id;

    @NotNull
    @NonNull
    private String originalName;

    @NotNull
    @NonNull
    private String name;

    @NotNull
    @NonNull
    private String originalText;

    @NotNull
    @NonNull
    private String text;

    @NotNull
    @NonNull
    private Integer originalSummoningRequirement;

    @NotNull
    @NonNull
    private Integer summoningRequirement;

}
