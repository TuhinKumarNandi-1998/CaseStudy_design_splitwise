package com.example.casestudy_design_splitwise.Repositories;

import com.example.casestudy_design_splitwise.Models.Expense;
import com.example.casestudy_design_splitwise.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    //Can this be done ?
    //I want to find all those expenses wherever the participants matches user in the expense
    //In DB table ->
    // SELECT * FROM
    //  Expense e JOIN Expense_Participants ep ON(e.id=ep.expense_id)
    //  JOIN User u ON(ep.participants_id=u.id)
    List<Expense> findAllByParticipants(User user);
}
