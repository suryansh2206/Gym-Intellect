package com.KnowIt.Gym_intellect_Crud.Controllers;


import com.KnowIt.Gym_intellect_Crud.Entity.DietMeal;
import com.KnowIt.Gym_intellect_Crud.Services.DietMealService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dietmeals")
public class DietMealController {

    @Autowired
    private DietMealService dietMealService;

    @GetMapping
    public List<DietMeal> getAllDietMeals() {
        return dietMealService.getAllDietMeals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietMeal> getDietMealById(@PathVariable Long id) {
        Optional<DietMeal> dietMeal = dietMealService.getDietMealById(id);
        return dietMeal.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DietMeal createDietMeal(@RequestBody DietMeal dietMeal) {
        return dietMealService.createDietMeal(dietMeal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietMeal> updateDietMeal(@PathVariable Long id, @RequestBody DietMeal updatedDietMeal) {
        try {
            DietMeal dietMeal = dietMealService.updateDietMeal(id, updatedDietMeal);
            return ResponseEntity.ok(dietMeal);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDietMeal(@PathVariable Long id) {
        dietMealService.deleteDietMeal(id);
        return ResponseEntity.noContent().build();
    }
}
