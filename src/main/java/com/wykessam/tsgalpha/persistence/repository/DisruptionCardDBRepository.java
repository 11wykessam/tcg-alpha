package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.card.DisruptionCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Repository responsible for accessing {@link DisruptionCard} objects.
 */
@Repository
public interface DisruptionCardDBRepository extends ReactiveMongoRepository<DisruptionCard, UUID> {
}