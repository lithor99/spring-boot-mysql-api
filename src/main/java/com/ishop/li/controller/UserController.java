package com.ishop.li.controller;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ishop.li.model.User;
import com.ishop.li.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    // private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping()
    public User createUser(@RequestBody User user) {
        // String encodedPassword = passwordEncoder.encode(user.getPassword());
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User _user = userRepository.findById(id).orElse(null);
        if (_user != null) {
            _user.setUsername(user.getUsername());
            _user.setPassword(user.getPassword());
            return userRepository.save(_user);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
