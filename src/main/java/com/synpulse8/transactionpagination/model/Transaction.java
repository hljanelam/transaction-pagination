package com.synpulse8.transactionpagination.model;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique ID of transaction")
    private String id;

    @ApiModelProperty(value = "Amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "Currency")
    private Currency currency;

    @ApiModelProperty(value = "Account IBAN")
    private String accountIban;

    @ApiModelProperty(value = "Value date")
    private LocalDate valueDate;

    @ApiModelProperty(value = "Description of transaction")
    private String description;

    // Each transaction is either debit or credit
    @ApiModelProperty(value = "Indicator for debit transaction")
    private Boolean isDebit;

    @ApiModelProperty(value = "Indicator for being deleted")
    private Boolean deleted;

}
