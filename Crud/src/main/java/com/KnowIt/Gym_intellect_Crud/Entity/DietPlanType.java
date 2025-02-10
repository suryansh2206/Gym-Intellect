package com.KnowIt.Gym_intellect_Crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "diet_plan_type")
public class DietPlanType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="diet_plan_id")
    private Long dietPlanId;

    @Column(nullable = false, length = 50, unique = true)
    private String dietPlanName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

	public Long getDietPlanId() {
		return dietPlanId;
	}

	public void setDietPlanId(Long dietPlanId) {
		this.dietPlanId = dietPlanId;
	}

	public String getDietPlanName() {
		return dietPlanName;
	}

	public void setDietPlanName(String dietPlanName) {
		this.dietPlanName = dietPlanName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
