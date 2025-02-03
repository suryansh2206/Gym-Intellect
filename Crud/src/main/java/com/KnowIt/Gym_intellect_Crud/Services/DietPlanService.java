package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.DietPlan;
import com.KnowIt.Gym_intellect_Crud.Repository.DietPlanRepository;

@Service
public class DietPlanService {
	@Autowired
    private DietPlanRepository dietPlanRepository;
	
	public List<DietPlan> getAllDietPlans() {
        return dietPlanRepository.findAll();
    }

    public Optional<DietPlan> getDietPlanById(Long id) {
        return dietPlanRepository.findById(id);
    }

    public DietPlan createDietPlan(DietPlan dietPlan) {
        return dietPlanRepository.save(dietPlan);
    }

    public DietPlan updateDietPlan(Long id, DietPlan updatedDietPlan) {
        return dietPlanRepository.findById(id).map(dietPlan -> {
        	if (updatedDietPlan.getMealType() != null) {
                dietPlan.setMealType(updatedDietPlan.getMealType());
            }
            if (updatedDietPlan.getDescription() != null) {
                dietPlan.setDescription(updatedDietPlan.getDescription());
            }
            if (updatedDietPlan.getCalories() > 0) {
                dietPlan.setCalories(updatedDietPlan.getCalories());
            }
            if (updatedDietPlan.getWorkoutPlan() != null) {
                dietPlan.setWorkoutPlan(updatedDietPlan.getWorkoutPlan());
            }
            if (updatedDietPlan.getDietPlanType() != null) {
                dietPlan.setDietPlanType(updatedDietPlan.getDietPlanType());
            }
            return dietPlanRepository.save(dietPlan);
        }).orElseThrow(() -> new RuntimeException("DietPlan not found with ID: " + id));
    }

    public void deleteDietPlan(Long id) {
        dietPlanRepository.deleteById(id);
    }
}
