package com.example.personalfinance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/user/{userId}/monthly")
    public Report calculateMonthlyReport(@PathVariable("userId") Long userId) {
        return reportService.calculateMonthlyReport(userId);
    }
}