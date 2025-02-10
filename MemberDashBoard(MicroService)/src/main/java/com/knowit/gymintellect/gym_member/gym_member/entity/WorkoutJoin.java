package com.knowit.gymintellect.gym_member.gym_member.entity;

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
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private WorkoutPlan workoutPlan;
}
