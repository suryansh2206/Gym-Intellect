package com.KnowIt.Gym_intellect_Crud.Controllers;

import com.KnowIt.Gym_intellect_Crud.Entity.DietPlan;
import com.KnowIt.Gym_intellect_Crud.Services.DietPlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dietplans")
public class DietPlanController {

    @Autowired
    private DietPlanService dietPlanService;

    @GetMapping
    public List<DietPlan> getAllDietPlans() {
        return dietPlanService.getAllDietPlans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietPlan> getDietPlanById(@PathVariable Long id) {
        Optional<DietPlan> dietPlan = dietPlanService.getDietPlanById(id);
        return dietPlan.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DietPlan createDietPlan(@RequestBody DietPlan dietPlan) {
        return dietPlanService.createDietPlan(dietPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietPlan> updateDietPlan(@PathVariable Long id, @RequestBody DietPlan updatedDietPlan) {
        try {
            DietPlan dietPlan = dietPlanService.updateDietPlan(id, updatedDietPlan);
            return ResponseEntity.ok(dietPlan);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDietPlan(@PathVariable Long id) {
        dietPlanService.deleteDietPlan(id);
        return ResponseEntity.noContent().build();
    }
}