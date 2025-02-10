package com.KnowIt.Gym_intellect_Crud.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private MemberPlanJoin membership;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "gym_profile_id")
    private GymProfile gymProfile;
    
    @ManyToOne
    @JoinColumn(name = "plan_id")
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

	public MemberPlanJoin getMembership() {
		return membership;
	}

	public void setMembership(MemberPlanJoin membership) {
		this.membership = membership;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GymProfile getGymProfile() {
		return gymProfile;
	}

	public void setGymProfile(GymProfile gymProfile) {
		this.gymProfile = gymProfile;
	}

	public WorkoutPlan getWorkoutPlan() {
		return workoutPlan;
	}

	public void setWorkoutPlan(WorkoutPlan workoutPlan) {
		this.workoutPlan = workoutPlan;
	}
    
    // Getters and setters
    
    
}

