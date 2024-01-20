package com.example.casestudy_design_splitwise.Services.SettleUpService.Strategy;

import com.example.casestudy_design_splitwise.Models.Expense;
import com.example.casestudy_design_splitwise.Services.SettleUpService.Transactions;

import java.util.List;

public interface SettleUpCalculatorStrategy {
    public List<Transactions> computeTransactions(List<Expense> expenses);
}
