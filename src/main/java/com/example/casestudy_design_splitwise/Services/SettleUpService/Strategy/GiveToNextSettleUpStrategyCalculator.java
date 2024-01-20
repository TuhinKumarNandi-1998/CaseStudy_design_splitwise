package com.example.casestudy_design_splitwise.Services.SettleUpService.Strategy;

import com.example.casestudy_design_splitwise.Models.Expense;
import com.example.casestudy_design_splitwise.Models.ExpenseOwingUser;
import com.example.casestudy_design_splitwise.Models.ExpensePayingUser;
import com.example.casestudy_design_splitwise.Models.User;
import com.example.casestudy_design_splitwise.Services.SettleUpService.Transactions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class GiveToNextSettleUpStrategyCalculator implements SettleUpCalculatorStrategy {
    @Override
    public List<Transactions> computeTransactions(List<Expense> expenses) {
        Map<User, Double> expenseUser = new HashMap<>();

        for(Expense expense : expenses) {
            for(ExpensePayingUser expensePayingUser : expense.getExpensePayingUsers()) {
                expenseUser.put(expensePayingUser.getUser(), expensePayingUser.getAmount());
            }
            for(ExpenseOwingUser expenseOwingUser : expense.getExpenseOwingUsers()) {
                if(expenseUser.containsKey(expenseOwingUser.getUser())) {
                    Double amt = expenseUser.get(expenseOwingUser.getUser());
                    expenseUser.put(expenseOwingUser.getUser(), amt-expenseOwingUser.getAmount());
                }
                else {
                    expenseUser.put(expenseOwingUser.getUser(), expenseOwingUser.getAmount());
                }
            }
        }

        //converting the expenseUser map into list
        List<Map.Entry<User, Double>> entries = new ArrayList<>(expenseUser.entrySet());
        List<Transactions> ans = new ArrayList<>();
        for (int i=0; i<entries.size()-1; i++) {
            Transactions transactions = new Transactions();
            if(entries.get(i).getValue() < 0) {
                transactions.setFromUser(entries.get(i).getKey());
                transactions.setToUser(entries.get(i+1).getKey());
                transactions.setAmount(Math.abs(entries.get(i).getValue()));

                entries.get(i+1).setValue(entries.get(i+1).getValue()+entries.get(i).getValue());
                entries.get(i).setValue(0.);
            }
            else if(entries.get(i).getValue() > 0) {
                transactions.setFromUser(entries.get(i+1).getKey());
                transactions.setToUser(entries.get(i).getKey());
                transactions.setAmount(Math.abs(entries.get(i).getValue()));

                entries.get(i+1).setValue(entries.get(i+1).getValue()+entries.get(i).getValue());
                entries.get(i).setValue(0.);
            }
            ans.add(transactions);
        }
        return ans;
    }
}
