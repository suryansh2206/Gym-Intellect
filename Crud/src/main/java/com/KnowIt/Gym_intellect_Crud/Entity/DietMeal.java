package com.KnowIt.Gym_intellect_Crud.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "diet_meal")
public class DietMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private int mealId;

    @ManyToOne
    @JoinColumn(name = "diet_id", referencedColumnName = "diet_id")
    private DietPlan dietPlan;

    @Column(name = "food_item", nullable = false)
    private String foodItem;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false)
    private float calories;

    // Getters and Setters
}
