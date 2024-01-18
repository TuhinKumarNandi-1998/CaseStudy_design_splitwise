package com.example.casestudy_design_splitwise.Commands.Registry;

import com.example.casestudy_design_splitwise.Commands.Command;

public interface RegisterCommand {
    public void addCommand(Command command);
    public void removeCommand(Command command);
    public Command getCommand(String input);
    public boolean execute(String input);
}
