package com.knowit.gymintellect.gym_member.gym_member.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "member")
public class Member {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    
    private Date dob;
    private String gender;
    private String address;
    private int height;
    
    @ManyToOne
    @JoinColumn(name = "membership_id")
    @JsonIgnore
    private MembershipPlanJoin membership;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    
    private int gym_profile_id;
    
    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "planId")
    @JsonIgnore
    private WorkoutPlan workoutPlan;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public MembershipPlanJoin getMembership() {
		return membership;
	}

	public void setMembership(MembershipPlanJoin membership) {
		this.membership = membership;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getGymProfile() {
		return gym_profile_id;
	}

	public void setGymProfile(int gymProfile) {
		this.gym_profile_id = gymProfile;
	}

	public WorkoutPlan getWorkoutPlan() {
		return workoutPlan;
	}

	public void setWorkoutPlan(WorkoutPlan workoutPlan) {
		this.workoutPlan = workoutPlan;
	}
    
    // Getters and setters
    
    
}

