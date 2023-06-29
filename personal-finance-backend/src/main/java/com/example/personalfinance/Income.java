package com.example.personalfinance;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import com.example.personalfinance.User;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Income {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter @Getter private Long id;

    @NotNull @Setter @Getter private BigDecimal amount;

    @NotNull @Setter @Getter private String source;

    @NotNull @Setter @Getter LocalDate date;

    @JsonIgnore
    @NotNull  @Setter @Getter @ManyToOne User user;
}

