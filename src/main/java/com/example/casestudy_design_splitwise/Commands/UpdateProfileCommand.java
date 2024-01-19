package com.example.casestudy_design_splitwise.Commands;

import com.example.casestudy_design_splitwise.Controllers.UserController;
import com.example.casestudy_design_splitwise.dtos.UpdateUserRequestDTO;
import com.example.casestudy_design_splitwise.dtos.UpdateUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class UpdateProfileCommand implements Command {
    private UserController userController;

    @Autowired
    public UpdateProfileCommand(UserController userController) {
        this.userController = userController;
    }

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
        UpdateUserRequestDTO requestDTO = new UpdateUserRequestDTO();
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        requestDTO.setUserId(Long.parseLong(inputWords.get(0)));
        requestDTO.setPassword(inputWords.get(2));
        UpdateUserResponseDTO updateUserResponseDTO = userController.updateUser(requestDTO);

        System.out.println(updateUserResponseDTO.getUserName());
    }
}
