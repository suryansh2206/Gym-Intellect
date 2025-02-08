package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.DietMeal;
import com.KnowIt.Gym_intellect_Crud.Repository.DietMealRepository;

@Service
public class DietMealService {
    @Autowired
    private DietMealRepository repository;

    public DietMeal save(DietMeal dietMeal) {
        return repository.save(dietMeal);
    }

    public List<DietMeal> getAll() {
        return repository.findAll();
    }

    public Optional<DietMeal> getById(int id) {
        return repository.findById(id);
    }

    public DietMeal update(int id, DietMeal updatedDietMeal) {
        return repository.findById(id).map(dietMeal -> {
            dietMeal.setFoodItem(updatedDietMeal.getFoodItem());
            dietMeal.setQuantity(updatedDietMeal.getQuantity());
            dietMeal.setCalories(updatedDietMeal.getCalories());
            
            if (updatedDietMeal.getDietPlan() != null) {
                dietMeal.setDietPlan(updatedDietMeal.getDietPlan());
            }
            return repository.save(dietMeal);
        }).orElseThrow(() -> new RuntimeException("DietMeal not found"));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
