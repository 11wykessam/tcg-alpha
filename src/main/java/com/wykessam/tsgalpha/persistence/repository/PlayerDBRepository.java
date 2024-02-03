package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.player.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 * Repository responsible for accessing {@link Player} objects.
 */
@Repository
public interface PlayerDBRepository extends ReactiveMongoRepository<Player, UUID> {
}
