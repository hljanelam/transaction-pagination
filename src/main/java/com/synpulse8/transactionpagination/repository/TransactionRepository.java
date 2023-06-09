package com.synpulse8.transactionpagination.repository;

import com.synpulse8.transactionpagination.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String>,
        PagingAndSortingRepository<Transaction, String>{

    List<Transaction> findByValueDate(LocalDate valueDate, Pageable pageable);

    // start date inclusive
    @Query("{ $and: [ { 'valueDate': { $gte: ?0, $lte: ?1 } }, { 'accountIban': { $eq: ?2 } } ] }")
    Page<Transaction> findByValueDateBetween(LocalDate start, LocalDate end, Pageable pageable, String accountIban);
}
