package com.synpulse8.transactionpagination.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class CurrencyRateDTO {

    private Boolean success;

    private String timestamp;

    private String base;

    private String date;

    private Map<String, BigDecimal> rates;

}
