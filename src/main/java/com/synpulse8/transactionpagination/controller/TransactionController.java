package com.synpulse8.transactionpagination.controller;

import com.synpulse8.transactionpagination.model.Transaction;
import com.synpulse8.transactionpagination.model.User;
import com.synpulse8.transactionpagination.payload.response.PaginatedResponse;
import com.synpulse8.transactionpagination.service.CurrencyRateService;
import com.synpulse8.transactionpagination.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CurrencyRateService currencyRateService;

    @GetMapping("/getAllTransactions")
    public List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/findByDate")
    public List<Transaction> findByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate date,
                                        @RequestParam("pageSize") int pageSize,
                                        @RequestParam("pageIndex") int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("valueDate").ascending());
        return transactionService.findByDate(date, pageable);
    }

    @PostMapping("/findByArbitraryMonth")
    public ResponseEntity<PaginatedResponse<Transaction>> findByArbitraryMonth(@RequestParam("month") int month,
                                                                                 @RequestParam("year") int year,
                                                                                 @RequestParam(name="pageSize", defaultValue="4") int pageSize,
                                                                                 @RequestParam(name="pageIndex", defaultValue="0") int pageIndex) {

        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("valueDate").ascending());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<String> accountIbanList = user.getAccountIban();
        List<Page<Transaction>> pages = new ArrayList<>();

        for (String accountIban : accountIbanList) {
            pages.add(transactionService.findByArbitraryMonth(year, month, pageable, accountIban));
        }
        List<Transaction> tmp = pages.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
        Page<Transaction> transactions = transformToPagesOfTransaction(tmp);

        List<Transaction> transactionList = transactions.getContent();
        BigDecimal totalValueInDebit = BigDecimal.ZERO;
        BigDecimal totalValueInCredit = BigDecimal.ZERO;

        Map<String, BigDecimal> rates = currencyRateService.getLatestCurrencyRates().getRates();

        for (Transaction transaction: transactionList) {
            BigDecimal tmpValue = BigDecimal.ZERO;
            tmpValue = tmpValue.add(transaction.getAmount().multiply(rates.get(transaction.getCurrency().getCurrencyCode())));
            if (transaction.getIsDebit()){
                totalValueInDebit = totalValueInDebit.add(tmpValue);
            } else {
                totalValueInCredit = totalValueInCredit.add(tmpValue);
            }
        }

        PaginatedResponse<Transaction> response = new PaginatedResponse<>();
        response.setData(transactions.getContent());
        response.setPageNumber(transactions.getNumber());
        response.setPageSize(transactions.getSize());
        response.setTotalItems(transactions.getTotalElements());
        response.setTotalPages(transactions.getTotalPages());
        response.setTotalValueInDebit(totalValueInDebit.setScale(2, BigDecimal.ROUND_HALF_EVEN));
        response.setTotalValueInCredit(totalValueInCredit.setScale(2, BigDecimal.ROUND_HALF_EVEN));

        return ResponseEntity.ok(response);
    }

    public Page<Transaction> transformToPagesOfTransaction (List<Transaction> transactions) {
        Page<Transaction> result = new PageImpl<>(transactions);
        return result;
    }

    @PostMapping("/addTransaction")
    @Transactional
    public ResponseEntity addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteTransaction(@PathVariable String id) {
        return transactionService.deleteTransaction(id);
    }

}
