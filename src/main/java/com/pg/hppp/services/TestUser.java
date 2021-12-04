package com.pg.hppp.services;

import com.pg.hppp.model.User;
import com.pg.hppp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

// Used temporarily for putting specific user for testing. todo to be removed after successful testing of logged in user

@AllArgsConstructor
@Service
public class TestUser {

    private final UserRepository userRepository;

    public User getTestUser(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
