package com.example.personalfinance;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ReportService {
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final InvestmentRepository investmentRepository;

    public ReportService(
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository,
            InvestmentRepository investmentRepository
    ) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.investmentRepository = investmentRepository;
    }


    public Report calculateMonthlyReport(Long userId) {
        LocalDate currentDate = LocalDate.now();
        LocalDate lastMonthStartDate = currentDate.withDayOfMonth(1);
        LocalDate lastMonthEndDate = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

        BigDecimal totalIncome = incomeRepository.calculateTotalIncome(userId, lastMonthStartDate, lastMonthEndDate);
        BigDecimal totalExpenses = expenseRepository.calculateTotalExpenses(userId, lastMonthStartDate, lastMonthEndDate);
        BigDecimal totalInvestments = investmentRepository.calculateTotalInvestments(userId, lastMonthStartDate, lastMonthEndDate);

        return new Report(totalIncome, totalExpenses, totalInvestments);
    }
}