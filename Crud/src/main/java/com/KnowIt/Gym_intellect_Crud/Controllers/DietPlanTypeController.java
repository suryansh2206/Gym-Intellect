package com.KnowIt.Gym_intellect_Crud.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KnowIt.Gym_intellect_Crud.Entity.DietPlanType;
import com.KnowIt.Gym_intellect_Crud.Services.DietPlanTypeService;

@RestController
@RequestMapping("/api/dietPlanTypes")
public class DietPlanTypeController {
    @Autowired
    private DietPlanTypeService service;

    @PostMapping
    public ResponseEntity<DietPlanType> create(@RequestBody DietPlanType dietPlanType) {
        return ResponseEntity.ok(service.save(dietPlanType));
    }

    @GetMapping
    public ResponseEntity<List<DietPlanType>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietPlanType> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietPlanType> update(@PathVariable Long id, @RequestBody DietPlanType dietPlanType) {
        return ResponseEntity.ok(service.update(id, dietPlanType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
