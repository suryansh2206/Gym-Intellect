package com.KnowIt.Gym_intellect_Crud.Controllers;


import com.KnowIt.Gym_intellect_Crud.Entity.DietMeal;
import com.KnowIt.Gym_intellect_Crud.Services.DietMealService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dietMeals")
public class DietMealController {
    @Autowired
    private DietMealService service;

    @PostMapping
    public ResponseEntity<DietMeal> create(@RequestBody DietMeal dietMeal) {
        return ResponseEntity.ok(service.save(dietMeal));
    }

    @GetMapping
    public ResponseEntity<List<DietMeal>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietMeal> getById(@PathVariable int id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietMeal> update(@PathVariable int id, @RequestBody DietMeal dietMeal) {
        return ResponseEntity.ok(service.update(id, dietMeal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
