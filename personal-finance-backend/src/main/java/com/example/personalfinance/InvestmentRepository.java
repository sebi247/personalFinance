package com.example.personalfinance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Investment i WHERE i.user.id = :userId AND i.date >= :startDate AND i.date <= :endDate")
    BigDecimal calculateTotalInvestments(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
