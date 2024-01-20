package com.example.casestudy_design_splitwise.Repositories;

import com.example.casestudy_design_splitwise.Models.Expense;
import com.example.casestudy_design_splitwise.Models.ExpenseOwingUser;
import com.example.casestudy_design_splitwise.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseOwingUserRepository extends JpaRepository<ExpenseOwingUser, Long> {
    List<ExpenseOwingUser> findAllByUser(User user);
}
