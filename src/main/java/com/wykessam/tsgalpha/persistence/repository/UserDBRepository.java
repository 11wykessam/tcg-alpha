package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.user.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 * Repository responsible for accessing {@link User} objects.
 */
@Repository
public interface UserDBRepository extends ReactiveMongoRepository<User, String> {

    /**
     * Extract user by their username.
     * This should be unique.
     *
     * @param username {@link String}.
     * @return {@link User}.
     */
    Mono<User> findFirstByUsername(final String username);

}