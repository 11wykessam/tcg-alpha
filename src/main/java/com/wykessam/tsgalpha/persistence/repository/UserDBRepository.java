package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author Samuel Wykes.
 */
@Repository
public interface UserDBRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findFirstByUsername(final String username);

}