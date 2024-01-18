package com.example.casestudy_design_splitwise.Commands;

public interface Command {
    public boolean parse(String input);
    public void execute(String input);
}
