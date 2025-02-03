package com.KnowIt.Gym_intellect_Crud.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diet_meal")
public class DietMeal {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealId;

    @ManyToOne
    @JoinColumn(name = "diet_id", referencedColumnName = "diet_id", nullable = false)
    private DietPlan dietPlan;

    @Column(nullable = false, length = 50)
    private String foodItem;

    @Column(nullable = false, length = 20)
    private String quantity;

    @Column(nullable = false)
    private float calories;
    
 // Getters and Setters

	public int getMealId() {
		return mealId;
	}

	public void setMealId(int mealId) {
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

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}
}
