package com.securedEdgePay.service;

import com.securedEdgePay.model.User;
import com.securedEdgePay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User user){
        user.setCreatedAt(new Date());
        user.setPassword(new BCryptPasswordEncoder().encode("welcome"));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}
