package com.knowit.gymintellect.gym_intellect.service;

import com.knowit.gymintellect.gym_intellect.dto.GymProfileRequest;
import com.knowit.gymintellect.gym_intellect.entity.GymProfile;
import com.knowit.gymintellect.gym_intellect.entity.User;
import com.knowit.gymintellect.gym_intellect.entity.WorkoutPlan;
import com.knowit.gymintellect.gym_intellect.repository.GymProfileRepository;
import com.knowit.gymintellect.gym_intellect.repository.UserRepository;
import com.knowit.gymintellect.gym_intellect.repository.WorkoutPlanRespository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymProfileService {

    @Autowired
    private GymProfileRepository gymProfileRepository;

    @Autowired
    private UserRepository userRepository;
    

    private static final Logger logger = LoggerFactory.getLogger(GymProfileService.class);

    @Transactional
    public GymProfile createGymProfile(GymProfileRequest gymProfileRequest) {
        logger.info("Creating gym profile for user ID: {}", gymProfileRequest.getUserId());
        System.out.println(gymProfileRequest);

        try {
            // Retrieve the user (gym owner) by ID
            User gymOwner = userRepository.findById(gymProfileRequest.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + gymProfileRequest.getUserId()));

            // Create a new GymProfile
            GymProfile gymProfile = new GymProfile();
            gymProfile.setGymName(gymProfileRequest.getGymName());
            gymProfile.setLocation(gymProfileRequest.getLocation());
            gymProfile.setContact(gymProfileRequest.getContact());

            // Set optional openHours field if provided
            if (gymProfileRequest.getOpenHours() != null && !gymProfileRequest.getOpenHours().isEmpty()) {
                gymProfile.setOpenHours(gymProfileRequest.getOpenHours());
            }

            gymProfile.setGst(gymProfileRequest.getGst());
            gymProfile.setOwner(gymOwner);
            gymProfile.setStatus("PENDING"); // Default status

            System.out.println(gymProfile);

            // Save and return the GymProfile
            return gymProfileRepository.save(gymProfile);

        } catch (DataIntegrityViolationException e) {
            // Handle database constraint violations (e.g., duplicate entries)
            logger.error("Database error while creating gym profile: {}", e.getMessage());
            throw new RuntimeException("Error while saving gym profile. Please check the data and try again.");
        } catch (Exception e) {
            // Catch any other general exceptions
            logger.error("Error while creating gym profile: {}", e.getMessage());
            throw new RuntimeException("An unexpected error occurred while creating the gym profile.");
        }
    }


    public List<GymProfile> getPendingGymProfiles() {
        logger.info("Fetching all pending gym profiles");
        return gymProfileRepository.findByStatus("PENDING");
    }

    public GymProfile getGymProfileById(Long gymProfileId) {
        logger.info("Fetching gym profile with ID: {}", gymProfileId);
        return gymProfileRepository.findById(gymProfileId)
                .orElseThrow(() -> new RuntimeException("Gym Profile not found with ID: " + gymProfileId));
    }

    @Transactional
    public GymProfile approveGymProfile(Long gymProfileId) {
        logger.info("Approving gym profile with ID: {}", gymProfileId);
        GymProfile gymProfile = getGymProfileById(gymProfileId);
        gymProfile.setStatus("APPROVED");
        return gymProfileRepository.save(gymProfile);
    }

    @Transactional
    public GymProfile rejectGymProfile(Long gymProfileId) {
        logger.info("Rejecting gym profile with ID: {}", gymProfileId);
        GymProfile gymProfile = getGymProfileById(gymProfileId);
        gymProfile.setStatus("REJECTED");
        return gymProfileRepository.save(gymProfile);
    }

    @Transactional
    public void deleteGymProfile(Long gymProfileId) {
        logger.info("Deleting gym profile with ID: {}", gymProfileId);
        GymProfile gymProfile = getGymProfileById(gymProfileId);
        gymProfileRepository.delete(gymProfile);
    }
}
