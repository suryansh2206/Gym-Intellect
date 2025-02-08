package com.knowit.gymintellect.gym_member.gym_member.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "diet_plan")
public class DietPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dietPlanId;

    @Column(unique = true, nullable = false)
    private String dietPlanName;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "dietPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DietMeal> meals;

    @OneToOne
    @JoinColumn(name = "workout_plan_id", referencedColumnName = "planId")
    private WorkoutPlan workoutPlan;

    // Getters and Setters
    public Long getDietPlanId() { return dietPlanId; }
    public void setDietPlanId(Long dietPlanId) { this.dietPlanId = dietPlanId; }

    public String getDietPlanName() { return dietPlanName; }
    public void setDietPlanName(String dietPlanName) { this.dietPlanName = dietPlanName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<DietMeal> getMeals() { return meals; }
    public void setMeals(List<DietMeal> meals) { this.meals = meals; }

    public WorkoutPlan getWorkoutPlan() { return workoutPlan; }
    public void setWorkoutPlan(WorkoutPlan workoutPlan) { this.workoutPlan = workoutPlan; }
}
