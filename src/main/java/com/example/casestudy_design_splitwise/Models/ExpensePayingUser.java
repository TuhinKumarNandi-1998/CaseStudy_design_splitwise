package com.example.casestudy_design_splitwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExpensePayingUser extends BaseModel {
    @ManyToOne
    private Expense expense;
    @ManyToOne
    private User user;
    private double amount;
}
