package com.example.casestudy_design_splitwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel {
    private String description;
    private double amount;
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private List<User> participants;
    @ManyToOne
    private Currency baseCurrency;
    @OneToMany(mappedBy = "expense")
    private List<ExpensePayingUser> expensePayingUsers;
    @OneToMany(mappedBy = "expense")
    private List<ExpenseOwingUser> expenseOwingUsers;
}
