package com.example.personalfinance;

import com.example.personalfinance.Expense;
import com.example.personalfinance.Income;
import com.example.personalfinance.Investment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter @Getter Long id;

    @NotNull @Email @Setter @Getter private String email;

    @NotNull @Getter private String password;

    @NotNull @Size(min = 5, max = 30) @Setter @Getter private String name;

    @NotNull @Setter @Getter private LocalDate dateOfBirth;
    @NotNull @Setter @Getter private String phoneNumber;

    @Setter @Getter private BigDecimal balance = BigDecimal.valueOf(0);

    @Setter @Getter private LocalDate signInDate = LocalDate.now();

    @JsonIgnore
    @Setter @Getter private String profilePicturePath = "";

    @OneToMany(mappedBy = "user") @Setter @Getter private Set<Income> incomes;

    @OneToMany(mappedBy = "user") @Setter @Getter private Set<Expense> expenses;

    @OneToMany(mappedBy = "user") @Setter @Getter private Set<Investment> investments;

}
