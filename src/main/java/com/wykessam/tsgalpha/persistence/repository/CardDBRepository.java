package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.card.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Repository responsible for accessing {@link Card} objects.
 */
@Repository
public interface CardDBRepository extends ReactiveMongoRepository<Card, UUID> {
}
