package com.synpulse8.transactionpagination.service;

import com.synpulse8.transactionpagination.model.dto.CurrencyRateDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRateService {

    private static final String url = "http://api.exchangeratesapi.io/v1/latest?access_key=d6f3167228c052520b0f61356f4553c8";

    public CurrencyRateDTO getLatestCurrencyRates() {
        RestTemplate restTemplate = new RestTemplate();
        CurrencyRateDTO exchangeRates = restTemplate.getForObject(url, CurrencyRateDTO.class);
        return exchangeRates;
    }

}
