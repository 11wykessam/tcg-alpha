package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.card.Card;
import com.wykessam.tsgalpha.persistence.entity.card.ChampionCard;
import com.wykessam.tsgalpha.persistence.entity.card.CreatureCard;
import com.wykessam.tsgalpha.persistence.entity.card.DisruptionCard;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
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

    /**
     * Get all cards in the database.
     *
     * @return {@link Card}.
     */
    public Flux<Card> findAll() {
        return this.creatureCardDBRepository.findAll().cast(Card.class)
                .mergeWith(this.championCardDBRepository.findAll()).cast(Card.class)
                .mergeWith(this.disruptionCardDBRepository.findAll()).cast(Card.class);
    }

    /**
     * Delete all cards in the database.
     *
     * @return {@link Void}.
     */
    public Mono<Void> deleteAll() {
        return this.creatureCardDBRepository.deleteAll()
                .then(this.championCardDBRepository.deleteAll())
                .then(this.disruptionCardDBRepository.deleteAll());
    }

    /**
     * Save a card to the database.
     *
     * @param card {@link Card}.
     * @return {@link Card}.
     */
    public Mono<Card> save(final Card card) {
        if (card instanceof ChampionCard)
            return Mono.just(card).cast(ChampionCard.class)
                    .flatMap(this.championCardDBRepository::save);
        else if (card instanceof CreatureCard)
            return Mono.just(card).cast(CreatureCard.class)
                    .flatMap(this.creatureCardDBRepository::save);
        else if (card instanceof DisruptionCard)
            return Mono.just(card).cast(DisruptionCard.class)
                    .flatMap(this.disruptionCardDBRepository::save);
        else return Mono.error(new ClassCastException());
    }

}
