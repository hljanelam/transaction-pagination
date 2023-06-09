package com.synpulse8.transactionpagination.controller;

import com.synpulse8.transactionpagination.model.dto.CurrencyRateDTO;
import com.synpulse8.transactionpagination.service.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyRateController {

    @Autowired
    private CurrencyRateService currencyRateService;

    @GetMapping("api/auth/getLatestCurrencyRates")
    public CurrencyRateDTO getLatestCurrencyRates() {
        return currencyRateService.getLatestCurrencyRates();
    }
}
