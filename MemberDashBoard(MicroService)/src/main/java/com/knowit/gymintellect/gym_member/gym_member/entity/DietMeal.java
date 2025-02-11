package com.knowit.gymintellect.gym_member.gym_member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "diet_meal")
public class DietMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;

    private String foodItem;
    private String quantity;
    private float calories;

    @ManyToOne
    @JoinColumn(name = "diet_id", referencedColumnName = "dietPlanId")
    @JsonIgnore
    private DietPlan dietPlan;

    // Getters and Setters
    public Long getMealId() { return mealId; }
    public void setMealId(Long mealId) { this.mealId = mealId; }

    public String getFoodItem() { return foodItem; }
    public void setFoodItem(String foodItem) { this.foodItem = foodItem; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public float getCalories() { return calories; }
    public void setCalories(float calories) { this.calories = calories; }

    public DietPlan getDietPlan() { return dietPlan; }
    public void setDietPlan(DietPlan dietPlan) { this.dietPlan = dietPlan; }
}

