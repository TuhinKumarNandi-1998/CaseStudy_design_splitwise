package com.example.casestudy_design_splitwise.Services;

import com.example.casestudy_design_splitwise.Models.User;
import com.example.casestudy_design_splitwise.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(password);
        return userRepository.save(user);
    }
}
