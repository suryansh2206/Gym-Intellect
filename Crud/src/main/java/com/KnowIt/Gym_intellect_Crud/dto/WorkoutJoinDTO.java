package com.KnowIt.Gym_intellect_Crud.dto;

public class WorkoutJoinDTO {
	
	private Long wojId;
    private long workoutId;
    private String exerciseName;
    private long planId;
    private String planName;
	public Long getWojId() {
		return wojId;
	}
	public void setWojId(Long wojId) {
		this.wojId = wojId;
	}
	public long getWorkoutId() {
		return workoutId;
	}
	public void setWorkoutId(long workoutId) {
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
