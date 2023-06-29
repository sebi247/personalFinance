package com.example.personalfinance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    public final InvestmentService investmentService;
    private final InvestmentRepository investmentRepository;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private UserService userService;

    @Autowired
    public InvestmentController(InvestmentService investmentService, InvestmentRepository investmentRepository) {
        this.investmentService = investmentService;
        this.investmentRepository = investmentRepository;
    }

    @GetMapping
    public List<Investment> getAllInvestments(){
        return investmentService.getAllInvestments();
    }

    @GetMapping("/{id}")
    public Investment getInvestmentById(@PathVariable Long id){
        return investmentService.getInvestmentById(id);
    }

    @PostMapping
    public Investment createInvestment(@RequestBody InvestmentRequest investmentRequest){
        User user = userService.getUserById(investmentRequest.getUserid());
        Investment investment = new Investment();
        investment.setUser(user);
        investment.setAmount(investmentRequest.getAmount());
        investment.setDate(LocalDate.now());
        investment.setType(investmentRequest.getType());
        investment.setReturns(investmentRequest.getReturns());
        investment.setAmountAfterReturns(investmentRequest.getAmount());


        user.setBalance(user.getBalance().subtract(investmentRequest.getAmount()));
        userService.updateUser(user);

        investment = investmentService.createInvestment(investment);

        JobDetail job = JobBuilder.newJob(InvestmentUpdateJob.class)
                .withIdentity("job" + investment.getId())
                .usingJobData("investmentId", investment.getId())
                .build();

        LocalDate date = LocalDate.now();
        int monthLength = date.lengthOfMonth();

        Trigger trigger = TriggerBuilder.newTrigger()
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.MONTH))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(24 * monthLength)
                        .repeatForever())
                .build();


        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return investment;
    }

    @PutMapping("/{id}")
    public Investment updateInvestment(@PathVariable Long id,@RequestBody Investment investment){
        investment.setId(id);
        return investmentService.updateInvestment(investment);
    }

    @DeleteMapping("/{id}")
    public void deleteInvestment(@PathVariable Long id){
        investmentService.deleteInvestment(id);
    }

    @GetMapping("/user/{userId}")
    public List<Investment> getInvestmentsByUser(@PathVariable Long userId) {
        return investmentService.getInvestmentsByUserId(userId);
    }

    @PostMapping("/{userId}/cashOutInvestment/{investmentId}")
    public void cashOutInvestment(@PathVariable Long userId, @PathVariable Long investmentId) {
        User user = userService.getUserById(userId);
        Investment investment = investmentService.getInvestmentById(investmentId);
        if (investment == null) {
            throw new RuntimeException("Investment not found");
        }

        if (!investment.getCashedOut()) {
            investment.setCashedOut(true);
            BigDecimal cashOutAmount = investment.getAmount();
            user.setBalance(user.getBalance().add(cashOutAmount));
            userService.updateUser(user);
            investmentService.updateInvestment(investment);
        } else {
            throw new RuntimeException("Investment has already been cashed out");
        }
    }

}
