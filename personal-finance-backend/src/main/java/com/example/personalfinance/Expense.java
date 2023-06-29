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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Expense {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Setter Long id;

    @NotNull @Getter @Setter BigDecimal amount;

    @NotNull @Getter @Setter String category;

    @NotNull @Getter @Setter LocalDate date;

    @JsonIgnore
    @NotNull @Getter @Setter @ManyToOne
    User user;
}
