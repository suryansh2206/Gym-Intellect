package com.knowit.gymintellect.gym_intellect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.gymintellect.gym_intellect.entity.GymProfile;
import com.knowit.gymintellect.gym_intellect.entity.User;

@Repository
public interface GymProfileRepository extends JpaRepository<GymProfile, Long> {
	 // Find GymProfile by Gym Owner's User ID
	List<GymProfile> findByOwner(User owner);

    
    // Find all GymProfiles that are pending approval
    List<GymProfile> findByStatus(String status);
    
    // Custom query example: Find GymProfiles by name
    public List<GymProfile> findByGymNameContainingIgnoreCase(String gymName);

}
