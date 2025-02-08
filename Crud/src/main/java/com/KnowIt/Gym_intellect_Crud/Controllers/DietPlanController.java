package com.KnowIt.Gym_intellect_Crud.Controllers;

import com.KnowIt.Gym_intellect_Crud.Entity.DietPlan;
import com.KnowIt.Gym_intellect_Crud.Services.DietPlanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dietPlans")
public class DietPlanController {
    @Autowired
    private DietPlanService service;

    @PostMapping
    public ResponseEntity<DietPlan> create(@RequestBody DietPlan dietPlan) {
        return ResponseEntity.ok(service.save(dietPlan));
    }

    @GetMapping
    public ResponseEntity<List<DietPlan>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietPlan> getById(@PathVariable int id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietPlan> update(@PathVariable int id, @RequestBody DietPlan dietPlan) {
        return ResponseEntity.ok(service.update(id, dietPlan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}