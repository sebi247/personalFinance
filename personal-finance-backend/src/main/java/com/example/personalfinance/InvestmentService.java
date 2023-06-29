package com.example.personalfinance;

import com.example.personalfinance.Investment;
import com.example.personalfinance.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class InvestmentService {

    public final InvestmentRepository investmentRepository;

    @Autowired
    private UserService userService;


    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public List<Investment> getAllInvestments(){
        return investmentRepository.findAll();
    }

    public Investment getInvestmentById(Long id){
        return investmentRepository.findById(id).orElse(null);
    }

    public Investment createInvestment(Investment investment){
        return investmentRepository.save(investment);
    }

    public Investment updateInvestment(Investment investment){
        return investmentRepository.save(investment);
    }

    public void deleteInvestment(Long id){
        investmentRepository.deleteById(id);
    }

    public List<Investment> getInvestmentsByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return new ArrayList<>(user.getInvestments());
    }

}
