package com.wykessam.tsgalpha.persistence.repository;

import com.wykessam.tsgalpha.persistence.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Samuel Wykes.
 */
@Repository
public interface UserDBRepository extends ReactiveMongoRepository<User, String> {

}