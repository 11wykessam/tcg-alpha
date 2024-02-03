package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.board.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

/**
 * @author Samuel Wykes.
 */
public interface GameDBRepository extends ReactiveMongoRepository<Game, UUID> {

}
