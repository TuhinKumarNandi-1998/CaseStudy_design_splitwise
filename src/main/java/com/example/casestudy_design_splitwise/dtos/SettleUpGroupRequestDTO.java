package com.example.casestudy_design_splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SettleUpGroupRequestDTO {
    private long groupID;
    private long userID;
}
