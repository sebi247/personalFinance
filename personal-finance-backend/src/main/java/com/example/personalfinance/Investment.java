package com.example.personalfinance;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@NotNull @Getter @Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Investment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Setter @Getter Long id;

    BigDecimal amount;

    String type;

    LocalDate date;

    BigDecimal returns;

    BigDecimal amountAfterReturns;

    Boolean cashedOut = false;

    @JsonIgnore @ManyToOne
    User user;
}
