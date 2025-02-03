package com.KnowIt.Gym_intellect_Crud.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout_join")
public class Workout_join {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wojId;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    @JsonBackReference
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @JsonBackReference
    private Workout_plan plan;


	public int getWojId() {
		return wojId;
	}

	public void setWojId(int wojId) {
		this.wojId = wojId;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public Workout_plan getPlan() {
		return plan;
	}

	public void setPlan(Workout_plan plan) {
		this.plan = plan;
	}

    
}
