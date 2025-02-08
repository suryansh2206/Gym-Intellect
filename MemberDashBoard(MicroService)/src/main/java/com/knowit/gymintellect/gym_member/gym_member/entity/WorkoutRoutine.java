package com.knowit.gymintellect.gym_member.gym_member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout_routine", indexes = {
    @Index(name = "idx_member_id", columnList = "member_id"),
    @Index(name = "idx_assigned_date", columnList = "assigned_date")
})
public class WorkoutRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)  // Ensure assigned workout belongs to this plan
    private WorkoutPlan workoutPlan;

    public WorkoutPlan getWorkoutPlan() {
		return workoutPlan;
	}

	public void setWorkoutPlan(WorkoutPlan workoutPlan) {
		this.workoutPlan = workoutPlan;
	}

	@ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @Column(nullable = false)
    private LocalDate assignedDate;

    @Column(nullable = false)
    private int plannedSets;

    @Column(nullable = false)
    private int plannedReps;

    private int completedSets;
    private int completedReps;
    private float caloriesBurnt;
    private LocalDateTime completionTime;

    @Column(nullable = false)
    private boolean isCustomRoutine; // NEW - True if assigned by gym owner
    
    @Column(nullable = false)
    private boolean completed; // Marks workout as fully completed


    public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public WorkoutRoutine() {}

    public WorkoutRoutine(Member member, Workout workout, LocalDate assignedDate, int plannedSets, int plannedReps, boolean isCustomRoutine) {
        this.member = member;
        this.workout = workout;
        this.assignedDate = assignedDate;
        this.plannedSets = plannedSets;
        this.plannedReps = plannedReps;
        this.completedSets = 0;
        this.completedReps = 0;
        this.caloriesBurnt = 0;
        this.completionTime = null;
        this.isCustomRoutine = isCustomRoutine;
    }

	public Long getRoutineId() {
		return routineId;
	}

	public void setRoutineId(Long routineId) {
		this.routineId = routineId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public LocalDate getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}

	public int getPlannedSets() {
		return plannedSets;
	}

	public void setPlannedSets(int plannedSets) {
		this.plannedSets = plannedSets;
	}

	public int getPlannedReps() {
		return plannedReps;
	}

	public void setPlannedReps(int plannedReps) {
		this.plannedReps = plannedReps;
	}

	public int getCompletedSets() {
		return completedSets;
	}

	public void setCompletedSets(int completedSets) {
		this.completedSets = completedSets;
	}

	public int getCompletedReps() {
		return completedReps;
	}

	public void setCompletedReps(int completedReps) {
		this.completedReps = completedReps;
	}

	public float getCaloriesBurnt() {
		return caloriesBurnt;
	}

	public void setCaloriesBurnt(float caloriesBurnt) {
		this.caloriesBurnt = caloriesBurnt;
	}

	public LocalDateTime getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(LocalDateTime completionTime) {
		this.completionTime = completionTime;
	}

	public boolean isCustomRoutine() {
		return isCustomRoutine;
	}

	public void setCustomRoutine(boolean isCustomRoutine) {
		this.isCustomRoutine = isCustomRoutine;
	}


    // Getters and Setters
   
}
