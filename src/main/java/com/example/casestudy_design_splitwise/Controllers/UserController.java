package com.example.casestudy_design_splitwise.Controllers;

import com.example.casestudy_design_splitwise.dtos.RegisterUserRequestDTO;
import com.example.casestudy_design_splitwise.dtos.RegisterUserResponseDTO;
import com.example.casestudy_design_splitwise.dtos.ResponseStatus;
import com.example.casestudy_design_splitwise.Models.User;
import com.example.casestudy_design_splitwise.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO requestDTO) {
        RegisterUserResponseDTO registerUserResponseDTO = new RegisterUserResponseDTO();
        try {
            User user = userService.registerUser(requestDTO.getUserName(),
                    requestDTO.getPhoneNo(),
                    requestDTO.getPassword());

            registerUserResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            registerUserResponseDTO.setUserID(user.getId());
        }
        catch (Exception e) {
            registerUserResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
            System.out.println("User is Failed to register");
        }
        return registerUserResponseDTO;
    }

    public void updateUser() {

    }
}
