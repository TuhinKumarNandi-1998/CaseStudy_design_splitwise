package com.example.casestudy_design_splitwise.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "currencys")
public class Currency extends BaseModel {
    private String name;
}
