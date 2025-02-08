package com.KnowIt.Gym_intellect_Crud.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KnowIt.Gym_intellect_Crud.Entity.DietPlanType;
import com.KnowIt.Gym_intellect_Crud.Repository.DietPlanTypeRepository;

@Service
public class DietPlanTypeService {
    @Autowired
    private DietPlanTypeRepository repository;

    public DietPlanType save(DietPlanType dietPlanType) {
        return repository.save(dietPlanType);
    }

    public List<DietPlanType> getAll() {
        return repository.findAll();
    }

    public Optional<DietPlanType> getById(int id) {
        return repository.findById(id);
    }

    public DietPlanType update(int id, DietPlanType updatedDietPlanType) {
        return repository.findById(id).map(dietPlanType -> {
            dietPlanType.setDietPlanName(updatedDietPlanType.getDietPlanName());
            dietPlanType.setDescription(updatedDietPlanType.getDescription());
            return repository.save(dietPlanType);
        }).orElseThrow(() -> new RuntimeException("DietPlanType not found"));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
