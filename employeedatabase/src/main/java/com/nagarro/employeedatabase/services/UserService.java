package com.nagarro.employeedatabase.services;

import com.nagarro.employeedatabase.models.User;
import com.nagarro.employeedatabase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to validate user
    public boolean validate(String userName, String password) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }

        throw new RuntimeException("Invalid Credentials !!");
    }
}
