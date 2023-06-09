package com.synpulse8.transactionpagination.repository;

import com.synpulse8.transactionpagination.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{username:'?0'}")
    User findUserByUsername(String username);

    Optional<User> findByUsername(String username);


}
