package com.example.personalfinance;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Report {
    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;
    private BigDecimal totalInvestments;

    public Report(BigDecimal totalIncome, BigDecimal totalExpenses, BigDecimal totalInvestments) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.totalInvestments = totalInvestments;
    }


}