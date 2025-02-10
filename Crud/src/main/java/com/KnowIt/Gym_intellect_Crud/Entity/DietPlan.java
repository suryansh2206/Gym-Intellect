package com.KnowIt.Gym_intellect_Crud.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "diet_plan")
public class DietPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="diet_plan_id")
    private int dietId;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private WorkoutPlan plan;

    @Column(nullable = false, length = 50)
    private String mealType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Float calories;

    @ManyToOne
    @JoinColumn(name = "dietPlanId")
    private DietPlanType dietPlanType;

    @OneToMany(mappedBy = "dietPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DietMeal> dietMeals;

	public int getDietId() {
		return dietId;
	}

	public void setDietId(int dietId) {
		this.dietId = dietId;
	}

	public WorkoutPlan getPlan() {
		return plan;
	}

	public void setPlan(WorkoutPlan plan) {
		this.plan = plan;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getCalories() {
		return calories;
	}

	public void setCalories(Float calories) {
		this.calories = calories;
	}

	public DietPlanType getDietPlanType() {
		return dietPlanType;
	}

	public void setDietPlanType(DietPlanType dietPlanType) {
		this.dietPlanType = dietPlanType;
	}

	public List<DietMeal> getDietMeals() {
		return dietMeals;
	}

	public void setDietMeals(List<DietMeal> dietMeals) {
		this.dietMeals = dietMeals;
	}
}