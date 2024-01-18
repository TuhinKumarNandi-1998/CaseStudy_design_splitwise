package com.example.casestudy_design_splitwise.Commands;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class UpdateProfileCommand implements Command {
    @Override
    public boolean parse(String input) {
        List<String> commandWords = Arrays.stream(input.split(" ")).toList();
        if(commandWords.size() != 3) {
            System.out.println("This is not a Update Profile Command.");
            return false;
        }
        if(!commandWords.get(1).equalsIgnoreCase(CommandKeywords.UPDATE_PROFILE_COMMAND)) {
            System.out.println("This is not a Update Profile Command.");
            return false;
        }
        System.out.println("This is a Update Profile Command.");
        return true;
    }

    @Override
    public void execute(String input) {

    }
}
