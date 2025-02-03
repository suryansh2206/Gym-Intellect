package com.KnowIt.Gym_intellect_Crud.dto;

public class WorkoutJoinDTO {
	
	private int wojId;
    private int workoutId;
    private String exerciseName;
    private long planId;
    private String planName;
	public int getWojId() {
		return wojId;
	}
	public void setWojId(int wojId) {
		this.wojId = wojId;
	}
	public int getWorkoutId() {
		return workoutId;
	}
	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}
	public String getExerciseName() {
		return exerciseName;
	}
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	public long getPlanId() {
		return planId;
	}
	public void setPlanId(long l) {
		this.planId = l;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
    
    

}
