package com.knowit.gymintellect.gym_member.gym_member.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout_plan")
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "plan_id")
    private Long planId;  // Primary Key

    @Column(name = "plan_name", nullable = false, length = 50)
    private String planName;

    @Column(name = "duration", nullable = false, length = 50)
    private String duration;  // Example: "4 Weeks", "8 Weeks"

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;  // Workout details
    
    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Workout> workouts;
    
    @OneToOne(mappedBy = "workoutPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DietPlan dietPlan;

    public DietPlan getDietPlan() {
		return dietPlan;
	}


	public void setDietPlan(DietPlan dietPlan) {
		this.dietPlan = dietPlan;
	}


	public List<Workout> getWorkouts() {
		return workouts;
	}


	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}


	public WorkoutPlan() {}
    

    // Getters and Setters
    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
