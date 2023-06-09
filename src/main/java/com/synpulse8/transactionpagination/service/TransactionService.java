package com.synpulse8.transactionpagination.service;

import com.synpulse8.transactionpagination.model.Transaction;
import com.synpulse8.transactionpagination.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public ResponseEntity addTransaction(Transaction transaction) {
        try{
            transactionRepository.save(transaction);
            return ResponseEntity.ok().body("Added transaction!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    public List<Transaction> findByDate(LocalDate date, Pageable pageable) {
        return transactionRepository.findByValueDate(date, pageable);
    }

    public Page<Transaction> findByArbitraryMonth(int year, int month, Pageable pageable, String accountIban) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = LocalDate.of(year, month, Month.of(month).maxLength());
        log.info("start: {}, end: {}", start, end);
        return transactionRepository.findByValueDateBetween(start, end, pageable, accountIban);
    }

    public ResponseEntity deleteTransaction(String id) {
        try{
            transactionRepository.deleteById(id);
            return ResponseEntity.ok().body("Deleted transaction!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

//    public List<Transaction> findByAccountIban(String accountIban) {
//        return transactionRepository.findByAccountIban(accountIban);
//    }

//    public Page<Transaction>  findByDate(LocalDate date, int size) {
//
//        transactionRepository.findByValueDate(valueDate, pageable);
//    }
}
