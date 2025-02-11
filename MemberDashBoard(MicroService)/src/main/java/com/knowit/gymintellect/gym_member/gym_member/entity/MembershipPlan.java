package com.knowit.gymintellect.gym_member.gym_member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "membership_plan")
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "member_plan_id")
    private Long memberPlanId;  // Primary Key

    @Column(name = "plan_name", nullable = false, length = 255)
    private String planName;

    @Column(name = "duration", nullable = false, length = 100)
    private String duration;  // Example: "1 Month", "3 Months", "1 Year"

    @Column(name = "price", nullable = false, length = 100)
    private String price;  // Example: "999", "1999", "2999"

    @ManyToOne
    @JoinColumn(name = "gym_profile_id", referencedColumnName = "gymProfileId", nullable = false)
    @JsonIgnore
    private GymProfile gymProfile;

    public MembershipPlan() {}
	public Long getMemberPlanId() {
		return memberPlanId;
	}
	public void setMemberPlanId(Long memberPlanId) {
		this.memberPlanId = memberPlanId;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	 
	 
}

