package com.example.personalfinance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseRepository expenseRepository;
    @Autowired
    private UserService userService;

    @Autowired
    public ExpenseController(ExpenseService expenseService, ExpenseRepository expenseRepository) {
        this.expenseService = expenseService;
        this.expenseRepository= expenseRepository;
    }

    @GetMapping
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }

    @PostMapping
    public Expense createExpense(@RequestBody ExpenseRequest expenseRequest){
        User user = userService.getUserById(expenseRequest.getUserid());
        Expense expense = new Expense();
        expense.setUser(user);
        expense.setAmount(expenseRequest.getAmount());
        expense.setDate(LocalDate.now());
        expense.setCategory(expenseRequest.getCategory());

        user.setBalance(user.getBalance().subtract(expenseRequest.getAmount()));

        return expenseService.createExpense(expense);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense){
        expense.setId(id);
        return expenseService.updateExpense(expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id){
        expenseService.deteleExpense(id);
    }

    @GetMapping("/user/{userId}")
    public List<Expense> getIncomesByUserId(@PathVariable Long userId) {
        return expenseService.getExpensesByUserId(userId);
    }
}
