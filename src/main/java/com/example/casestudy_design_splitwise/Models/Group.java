package com.example.casestudy_design_splitwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "groupss")
public class Group extends BaseModel {
    private String name;
    private String description;
    @ManyToMany
    private List<User> participants;
    @ManyToMany
    private List<User> admins;
    @ManyToOne
    private User createdBy;
    @OneToMany
    private List<Expense> expenses;
    private Date createdAt;
}
