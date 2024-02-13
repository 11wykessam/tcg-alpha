package com.wykessam.tsgalpha.persistence.entity.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Samuel Wykes.
 * Represents a champion card in the TCG.
 */
@Document(collection = "card")
@TypeAlias(value = "disruption")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public final class DisruptionCard extends Card {
}
