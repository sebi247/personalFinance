package com.example.personalfinance;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

public class IncomeRequest {
    @Getter @Setter
    private BigDecimal amount;

    @Getter @Setter
    private String source;

    @Getter @Setter
    private Long userid;
}
