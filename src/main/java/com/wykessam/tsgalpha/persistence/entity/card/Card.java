package com.wykessam.tsgalpha.persistence.entity.card;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Represents a card in the TCG.
 */
@Document(collection = "card")
@Getter
@Builder
@RequiredArgsConstructor
public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Builder.Default
    private final UUID id = UUID.randomUUID();

}
