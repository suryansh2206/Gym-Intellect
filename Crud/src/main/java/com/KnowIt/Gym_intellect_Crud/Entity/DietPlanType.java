package com.KnowIt.Gym_intellect_Crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "diet_plan_type")
public class DietPlanType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dietPlan_id")
    private int dietPlanId;

    @Column(name = "dietPlan_name", nullable = false, unique = true, length = 50)
    private String dietPlanName;

    @Column(nullable = false)
    private String description;
}
