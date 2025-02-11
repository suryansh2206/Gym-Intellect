package com.knowit.gymintellect.gym_member.gym_member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "workout_join")
public class WorkoutJoin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wojId;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    @JsonIgnore
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    private WorkoutPlan workoutPlan;
}
