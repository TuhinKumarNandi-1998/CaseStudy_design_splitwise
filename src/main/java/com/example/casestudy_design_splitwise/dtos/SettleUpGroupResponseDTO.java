package com.example.casestudy_design_splitwise.dtos;

import com.example.casestudy_design_splitwise.Services.SettleUpService.Transactions;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpGroupResponseDTO {
    private List<Transactions> transactions;
}
