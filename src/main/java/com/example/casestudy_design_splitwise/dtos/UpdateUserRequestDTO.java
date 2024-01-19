package com.example.casestudy_design_splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequestDTO {
    private long userId;
    private String password;
}
