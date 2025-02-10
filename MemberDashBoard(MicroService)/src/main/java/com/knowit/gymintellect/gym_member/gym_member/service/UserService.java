package com.knowit.gymintellect.gym_member.gym_member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_member.gym_member.entity.User;
import com.knowit.gymintellect.gym_member.gym_member.repository.UserRepository;


@Service
public class UserService {
	 @Autowired
	    private UserRepository userRepository;

	    public Optional<User> getUserById(Long userId) {
	        return userRepository.findById(userId);
	    }
}
