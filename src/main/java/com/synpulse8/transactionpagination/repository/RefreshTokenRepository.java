package com.synpulse8.transactionpagination.repository;

import com.synpulse8.transactionpagination.model.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.bson.types.ObjectId;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    void deleteByOwner_Id(ObjectId id);
    default void deleteByOwner_Id(String id) {
        deleteByOwner_Id(new ObjectId(id));
    };
}
