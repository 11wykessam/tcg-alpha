package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.card.CreatureCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Repository responsible for accessing {@link CreatureCard} objects.
 */
@Repository
public interface CreatureCardDBRepository extends ReactiveMongoRepository<CreatureCard, UUID> {
}