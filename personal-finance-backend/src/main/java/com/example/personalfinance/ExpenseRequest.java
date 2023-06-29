package com.example.personalfinance;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ExpenseRequest {

    private BigDecimal amount;

    private String category;

    private Long userid;
}
