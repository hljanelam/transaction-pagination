package com.synpulse8.transactionpagination.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse<T> {

    private List<T> data;

    private int pageNumber;

    private int pageSize;

    private long totalItems;

    private int totalPages;

    private BigDecimal totalValueInDebit;

    private BigDecimal totalValueInCredit;

}
