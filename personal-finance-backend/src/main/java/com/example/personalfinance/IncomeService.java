package com.example.personalfinance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository ;

    @Autowired
    private UserService userService;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public List<Income> getAllIncomes(){
        return incomeRepository.findAll();
    }

    public Income getIncomeById(Long id){
        return incomeRepository.findById(id).orElse(null);
    }

    public Income createIncome(Income income){
        return incomeRepository.save(income);
    }

    public Income updateIncome(Income income){
        return incomeRepository.save(income);
    }

    public void deleteIncome(Long id){
        incomeRepository.deleteById(id);
    }

    public List<Income> getIncomesByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return new ArrayList<>(user.getIncomes());
    }


}
