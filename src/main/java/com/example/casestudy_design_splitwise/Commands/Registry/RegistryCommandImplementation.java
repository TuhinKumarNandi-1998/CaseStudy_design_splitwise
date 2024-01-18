package com.example.casestudy_design_splitwise.Commands.Registry;

import com.example.casestudy_design_splitwise.Commands.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RegistryCommandImplementation implements RegisterCommand {
    List<Command> commands = new ArrayList<>();
    @Override
    public void addCommand(Command command) {
        commands.add(command);
    }

    @Override
    public void removeCommand(Command command) {
        commands.remove(command);
    }

    @Override
    public Command getCommand(String input) {
        for(Command command : commands) {
            if(command.parse(input)) {
                return command;
            }
        }
        return null;
    }

    @Override
    public boolean execute(String input) {
        for(Command command : commands) {
            if(command.parse(input)) {
                command.execute(input);
                return true;
            }
        }
        return false;
    }
}
