package com.KnowIt.Gym_intellect_Crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "diet_meal")
public class DietMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;

    @ManyToOne
    @JoinColumn(name = "dietPlan_id")
    private DietPlan dietPlan;

    @Column(nullable = false, length = 50)
    private String foodItem;

    @Column(nullable = false, length = 20)
    private String quantity;

    @Column(nullable = false)
    private Float calories;

	public Long getMealId() {
		return mealId;
	}

	public void setMealId(Long mealId) {
		this.mealId = mealId;
	}

	public DietPlan getDietPlan() {
		return dietPlan;
	}

	public void setDietPlan(DietPlan dietPlan) {
		this.dietPlan = dietPlan;
	}

	public String getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Float getCalories() {
		return calories;
	}

	public void setCalories(Float calories) {
		this.calories = calories;
	}

    // Getters and Setters
}
