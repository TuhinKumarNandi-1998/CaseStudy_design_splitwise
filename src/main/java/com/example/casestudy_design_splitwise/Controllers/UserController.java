package com.example.casestudy_design_splitwise.Controllers;

import com.example.casestudy_design_splitwise.Exceptions.SamePreviousPasswordNotAllowed;
import com.example.casestudy_design_splitwise.Exceptions.UserNotFoundException;
import com.example.casestudy_design_splitwise.dtos.*;
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

    public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO) {
        UpdateUserResponseDTO updateUserResponseDTO = new UpdateUserResponseDTO();
        try {
            User user = userService.updateUser(updateUserRequestDTO.getUserId(),
                    updateUserRequestDTO.getPassword());

            updateUserResponseDTO.setUserName(user.getName());
            updateUserResponseDTO.setUserId(user.getId());
        }
        catch (UserNotFoundException e) {
            System.out.println("User with ID "+updateUserRequestDTO.getUserId()+ " not found");
        }
        catch (SamePreviousPasswordNotAllowed e) {
            System.out.println("Same password as previous not allowed.");
        }
        return updateUserResponseDTO;
    }
}
