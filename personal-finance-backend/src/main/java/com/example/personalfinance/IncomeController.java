package com.example.personalfinance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

    private final IncomeService incomeService;
    private final IncomeRepository incomeRepository;


    @Autowired
    private UserService userService;

    @Autowired
    public IncomeController(IncomeService incomeService, IncomeRepository incomeRepository) {
        this.incomeService = incomeService;
        this.incomeRepository = incomeRepository;
    }

    @GetMapping
    public List<Income> getAllIncomes(){
        return incomeService.getAllIncomes();
    }

    @GetMapping("/{id}")
    public Income getIncomeById(@PathVariable Long id){
        return incomeService.getIncomeById(id);
    }

    @PostMapping
    public Income createIncome(@RequestBody IncomeRequest incomeRequest) {
        User user = userService.getUserById(incomeRequest.getUserid());

        Income income = new Income();
        income.setUser(user);
        income.setAmount(incomeRequest.getAmount());
        income.setDate(LocalDate.now());
        income.setSource(incomeRequest.getSource());

        user.setBalance(user.getBalance().add(incomeRequest.getAmount()));

        return incomeService.createIncome(income);
    }

    @PutMapping("/{id}")
    public Income updateIncome(@PathVariable Long id,@RequestBody Income income){
        income.setId(id);
        return incomeService.updateIncome(income);
    }

    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable Long id){
        incomeService.deleteIncome(id);
    }

    @GetMapping("/user/{userId}")
    public List<Income> getIncomesByUserId(@PathVariable Long userId) {
        return incomeService.getIncomesByUserId(userId);
    }
}