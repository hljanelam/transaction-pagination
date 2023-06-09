package com.synpulse8.transactionpagination.payload;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Data
public class Transaction {

    private String id;

    private BigDecimal amount;

    private Currency currency;

    private String accountIban;

    private LocalDate valueDate;

    private String description;

    private Boolean isDebit;

    private Boolean deleted;

}
