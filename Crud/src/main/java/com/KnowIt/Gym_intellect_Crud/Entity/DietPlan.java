package com.KnowIt.Gym_intellect_Crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "diet_plan")
public class DietPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id")
    private int dietId;

    @JoinColumn(name = "plan_id", referencedColumnName = "planId")
    private Integer planId;

    @Column(name = "meal_type", nullable = false)
    private String mealType;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float calories;

    @ManyToOne
    @JoinColumn(name = "dietPlan_id", referencedColumnName = "dietPlan_id")
    private DietPlanType dietPlanType;

    // Getters and Setters
}
