package com.example.casestudy_design_splitwise.Repositories;

import com.example.casestudy_design_splitwise.Models.ExpensePayingUser;
import com.example.casestudy_design_splitwise.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensePayingUserRepository extends JpaRepository<ExpensePayingUser, Long> {
    List<ExpensePayingUser> findAllByUser(User user);
}
