package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;
import java.util.Optional;

import com.KnowIt.Gym_intellect_Crud.Entity.DietMeal;
import com.KnowIt.Gym_intellect_Crud.Repository.DietMealRepository;

public class DietMealService {
	private DietMealRepository dietMealRepository;
	
	public List<DietMeal> getAllDietMeals(){
		return dietMealRepository.findAll();
	}
	
	public DietMeal createDietMeal(DietMeal dietmeal) {
		return dietMealRepository.save(dietmeal);
	}
	
	public Optional<DietMeal> getDietMealById(Long id){
		return dietMealRepository.findById(id);
	}
	
	public DietMeal updateDietMeal(Long mealId, DietMeal updatedDietMeal) {
        return dietMealRepository.findById(mealId).map(dietMeal -> {
            if (updatedDietMeal.getFoodItem() != null) {
                dietMeal.setFoodItem(updatedDietMeal.getFoodItem());
            }
            if (updatedDietMeal.getQuantity() != null) {
                dietMeal.setQuantity(updatedDietMeal.getQuantity());
            }
            if (updatedDietMeal.getCalories() > 0) {
                dietMeal.setCalories(updatedDietMeal.getCalories());
            }
            if (updatedDietMeal.getDietPlan() != null) {
                dietMeal.setDietPlan(updatedDietMeal.getDietPlan());
            }
            return dietMealRepository.save(dietMeal);
        }).orElseThrow(() -> new RuntimeException("DietMeal not found with ID: " + mealId));
    }
	
	public void deleteDietMeal(Long id) {
        dietMealRepository.deleteById(id);
    }
}
