package com.KnowIt.Gym_intellect_Crud.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "workout_plan")
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "plan_id")
    private Long planId;

    @Column(nullable = false, length = 50)
    private String planName;

    @Column(nullable = false, length = 50)
    private String duration;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Workout_join> workoutJoins;

	public long getPlanId() {
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

	public List<Workout_join> getWorkoutJoins() {
		return workoutJoins;
	}

	public void setWorkoutJoins(List<Workout_join> workoutJoins) {
		this.workoutJoins = workoutJoins;
	}

   
}


