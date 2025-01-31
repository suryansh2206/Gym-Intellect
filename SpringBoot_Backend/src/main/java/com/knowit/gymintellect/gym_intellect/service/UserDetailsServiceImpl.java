package com.knowit.gymintellect.gym_intellect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_intellect.entity.User;
import com.knowit.gymintellect.gym_intellect.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Debug log to trace input username
        System.out.println("Attempting to load user with username: " + username);

        // Fetch user from database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username: " + username + ". Please check the username."));

        // Debug log to trace fetched user details
        System.out.println("User found: " + user.getUsername());
        System.out.println("Password from database: " + user.getPassword());
        System.out.println("Assigning role: " + user.getRole().getName());

        // Convert User entity to Spring Security User
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().getName()) // Assuming role is a simple String like "USER" or "ADMIN"
                .build();
    }
}
