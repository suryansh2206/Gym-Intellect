package com.KnowIt.Gym_intellect_Crud.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "diet_plan_type")
public class DietPlanType {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dietPlanId;

    @Column(nullable = false, unique = true, length = 50)
    private String dietPlanName;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "dietPlanType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DietPlan> dietPlans;

	public long getDietPlanId() {
		return dietPlanId;
	}

	public void setDietPlanId(int dietPlanId) {
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

	public List<DietPlan> getDietPlans() {
		return dietPlans;
	}

	public void setDietPlans(List<DietPlan> dietPlans) {
		this.dietPlans = dietPlans;
	}
}
