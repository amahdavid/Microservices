package org.microservices.userservice.service;

import org.microservices.userservice.excecptions.UserNotFoundException;
import org.microservices.userservice.model.User;
import org.microservices.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
}