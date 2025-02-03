package com.KnowIt.Gym_intellect_Crud.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diet_plan")
public class DietPlan {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dietId;

    @ManyToOne
    @JoinColumn(name = "workoutPlan_id", referencedColumnName = "plan_id", nullable = true)
    private Workout_plan workoutPlan;

    @ManyToOne
    @JoinColumn(name = "dietPlan_id", referencedColumnName = "dietPlan_id", nullable = true)
    private DietPlanType dietPlanType;

    @Column(nullable = false, length = 50)
    private String mealType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private float calories;

	public long getDietId() {
		return dietId;
	}

	public void setDietId(int dietId) {
		this.dietId = dietId;
	}

	public Workout_plan getWorkoutPlan() {
		return workoutPlan;
	}

	public void setWorkoutPlan(Workout_plan workoutPlan) {
		this.workoutPlan = workoutPlan;
	}

	public DietPlanType getDietPlanType() {
		return dietPlanType;
	}

	public void setDietPlanType(DietPlanType dietPlanType) {
		this.dietPlanType = dietPlanType;
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

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}
}
