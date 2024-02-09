package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.card.ChampionCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Repository responsible for accessing {@link ChampionCard} objects.
 */
@Repository
public interface ChampionCardDBRepository extends ReactiveMongoRepository<ChampionCard, UUID> {
}