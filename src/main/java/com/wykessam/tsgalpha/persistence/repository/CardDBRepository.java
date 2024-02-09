package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.card.Card;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Repository responsible for accessing {@link Card} objects.
 */
@Repository
@RequiredArgsConstructor
public class CardDBRepository {

    private final CreatureCardDBRepository creatureCardDBRepository;
    private final ChampionCardDBRepository championCardDBRepository;
    private final DisruptionCardDBRepository disruptionCardDBRepository;

    /**
     * Get a card of any subclass by its unique identifier.
     *
     * @param id {@link UUID}.
     * @return {@link Card}.
     */
    public Mono<Card> findById(@NonNull final UUID id) {
        return this.creatureCardDBRepository.findById(id).cast(Card.class)
                .switchIfEmpty(this.championCardDBRepository.findById(id).cast(Card.class))
                .switchIfEmpty(this.disruptionCardDBRepository.findById(id).cast(Card.class));
    }

}
