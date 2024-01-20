package com.example.casestudy_design_splitwise.Services.SettleUpService;

import com.example.casestudy_design_splitwise.Models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transactions {
    private User fromUser;
    private User toUser;
    private double amount;
}
