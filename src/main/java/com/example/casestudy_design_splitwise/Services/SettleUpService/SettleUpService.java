package com.example.casestudy_design_splitwise.Services.SettleUpService;

import com.example.casestudy_design_splitwise.Exceptions.UserNotFoundException;
import com.example.casestudy_design_splitwise.Models.*;
import com.example.casestudy_design_splitwise.Repositories.ExpenseOwingUserRepository;
import com.example.casestudy_design_splitwise.Repositories.ExpensePayingUserRepository;
import com.example.casestudy_design_splitwise.Repositories.GroupRepository;
import com.example.casestudy_design_splitwise.Repositories.UserRepository;
import com.example.casestudy_design_splitwise.Services.SettleUpService.Strategy.SettleUpCalculatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SettleUpService {
    private SettleUpCalculatorStrategy settleUpCalculatorStrategy;
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    private ExpensePayingUserRepository expensePayingUserRepository;
    private ExpenseOwingUserRepository expenseOwingUserRepository;

    public SettleUpService(SettleUpCalculatorStrategy settleUpCalculatorStrategy,
                           GroupRepository groupRepository,
                           UserRepository userRepository,
                           ExpensePayingUserRepository expensePayingUserRepository,
                           ExpenseOwingUserRepository expenseOwingUserRepository) {
        this.settleUpCalculatorStrategy = settleUpCalculatorStrategy;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.expensePayingUserRepository = expensePayingUserRepository;
        this.expenseOwingUserRepository = expenseOwingUserRepository;
    }

    //Check this please ->
    public List<Transactions> settleUpUser(long userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id "+userId+ " not found.");
        }

        List<ExpensePayingUser> expensePayingUsers = expensePayingUserRepository.findAllByUser(optionalUser.get());
        List<ExpenseOwingUser> expenseOwingUsers = expenseOwingUserRepository.findAllByUser(optionalUser.get());

        Set<Expense> expenseSet = new HashSet<>();
        for(ExpensePayingUser expensePayingUser : expensePayingUsers) {
            expenseSet.add(expensePayingUser.getExpense());
        }
        for(ExpenseOwingUser expenseOwingUser : expenseOwingUsers) {
            expenseSet.add(expenseOwingUser.getExpense());
        }

        return settleUpCalculatorStrategy.computeTransactions(new ArrayList<>(expenseSet));
    }

    //Check this please ->
    public List<Transactions> settleUpGroup(long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        List<Expense> expenses = optionalGroup.get().getExpenses();

        return settleUpCalculatorStrategy.computeTransactions(expenses);
    }
}
