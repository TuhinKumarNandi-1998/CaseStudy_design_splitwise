package com.example.casestudy_design_splitwise.Commands;

import com.example.casestudy_design_splitwise.Controllers.UserController;
import com.example.casestudy_design_splitwise.dtos.RegisterUserRequestDTO;
import com.example.casestudy_design_splitwise.dtos.RegisterUserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class RegisterUserCommand implements Command {
    private RegisterUserRequestDTO requestDTO;
    private UserController userController;

    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean parse(String input) {
        List<String> commandWords = Arrays.stream(input.split(" ")).toList();
        if(commandWords.size() != 4) {
            System.out.println("This is not a Register User Command.");
            return false;
        }
        if(!commandWords.get(0).equalsIgnoreCase(CommandKeywords.REGISTER_USER_COMMAND)) {
            System.out.println("This is not a Register User Command.");
            return false;
        }
        System.out.println("This is a Register User Command.");
        return true;
    }

    @Override
    public void execute(String input) {
        requestDTO = new RegisterUserRequestDTO();
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        String userName = inputWords.get(1);
        String phoneNo = inputWords.get(2);
        String password = inputWords.get(3);

        requestDTO.setUserName(userName);
        requestDTO.setPhoneNo(phoneNo);
        requestDTO.setPassword(password);


        RegisterUserResponseDTO registerUserResponseDTO = userController.registerUser(requestDTO);
//        System.out.println(registerUserResponseDTO.getResponseStatus());
        System.out.println(registerUserResponseDTO.getUserID());
    }
}
