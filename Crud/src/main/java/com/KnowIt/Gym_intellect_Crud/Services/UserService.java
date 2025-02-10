package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.User;
import com.KnowIt.Gym_intellect_Crud.Repository.UserRepository;

@Service
public class UserService {
	 @Autowired
	 private final UserRepository ownerRepository;

	    public UserService(UserRepository ownerRepository) {
	        this.ownerRepository = ownerRepository;
	    }

	    public Optional<User> getOwnerById(Long ownerId) {
	    	List<User> owners = ownerRepository.findByRole_Rid(2); // Get all owners
	        return owners.stream()
	                     .filter(user -> user.getUserId().equals(ownerId)) // Find the matching ownerId
	                     .findFirst();
	    }
}
