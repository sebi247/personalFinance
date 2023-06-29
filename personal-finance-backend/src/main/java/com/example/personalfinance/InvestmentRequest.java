package com.example.personalfinance;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter
public class InvestmentRequest { //DTO

    private BigDecimal amount;

    private String type;

    private BigDecimal returns;

    private Long userid;
}
