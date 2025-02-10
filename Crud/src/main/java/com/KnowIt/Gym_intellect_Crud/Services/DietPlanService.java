package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.DietPlan;
import com.KnowIt.Gym_intellect_Crud.Entity.DietPlanType;
import com.KnowIt.Gym_intellect_Crud.Repository.DietPlanRepository;
import com.KnowIt.Gym_intellect_Crud.Repository.DietPlanTypeRepository;

@Service
public class DietPlanService {
    @Autowired
    private DietPlanRepository repository;

    public DietPlan save(DietPlan dietPlan) {
    
        return repository.save(dietPlan);
    }

    public List<DietPlan> getAll() {
        return repository.findAll();
    }

    public Optional<DietPlan> getById(int id) {
        return repository.findById(id);
    }

    @Autowired
    private DietPlanTypeRepository dietPlanTypeRepository; // Inject DietPlanType repository

    public DietPlan update(int id, DietPlan updatedDietPlan) {
        return repository.findById(id).map(dietPlan -> {
            dietPlan.setMealType(updatedDietPlan.getMealType());
            dietPlan.setDescription(updatedDietPlan.getDescription());
            dietPlan.setCalories(updatedDietPlan.getCalories());

            if (updatedDietPlan.getPlan() != null) {
                dietPlan.setPlan(updatedDietPlan.getPlan());
            }

            if (updatedDietPlan.getDietPlanType() != null) {
                DietPlanType dietPlanType = dietPlanTypeRepository
                    .findById(updatedDietPlan.getDietPlanType().getDietPlanId())
                    .orElseThrow(() -> new RuntimeException("DietPlanType not found"));
                dietPlan.setDietPlanType(dietPlanType);
            }

            return repository.save(dietPlan);
        }).orElseThrow(() -> new RuntimeException("DietPlan not found"));
    }


    public void delete(int id) {
        repository.deleteById(id);
    }
}
