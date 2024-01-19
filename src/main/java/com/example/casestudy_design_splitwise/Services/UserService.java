package com.example.casestudy_design_splitwise.Services;

import com.example.casestudy_design_splitwise.Exceptions.SamePreviousPasswordNotAllowed;
import com.example.casestudy_design_splitwise.Exceptions.UserNotFoundException;
import com.example.casestudy_design_splitwise.Models.User;

import com.example.casestudy_design_splitwise.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String userName, String phoneNo, String password) {
        User user = new User();
        user.setName(userName);
        user.setPhoneNo(phoneNo);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User updateUser(Long userId, String password) throws UserNotFoundException, SamePreviousPasswordNotAllowed {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id "+userId+" not found");
        }
        User user = optionalUser.get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new SamePreviousPasswordNotAllowed("same password as previous not allowed");
        }
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }
}
