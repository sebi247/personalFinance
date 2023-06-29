package com.example.personalfinance;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public class InvestmentUpdateJob implements Job {

    @Autowired
    private InvestmentService investmentService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long investmentId = context.getJobDetail().getJobDataMap().getLong("investmentId");
        Investment investment = investmentService.getInvestmentById(investmentId);

        BigDecimal baseAmount = investment.getAmountAfterReturns() != null ?
                investment.getAmountAfterReturns() : investment.getAmount();

        BigDecimal updatedAmount = baseAmount.add(baseAmount.multiply(investment.getReturns()));
        investment.setAmountAfterReturns(updatedAmount);

        investmentService.updateInvestment(investment);
    }

}


