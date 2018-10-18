package com.securedEdgePay.service;

import com.securedEdgePay.model.User;
import com.securedEdgePay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}
