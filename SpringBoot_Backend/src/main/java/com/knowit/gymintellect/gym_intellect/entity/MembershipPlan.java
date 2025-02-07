package com.knowit.gymintellect.gym_intellect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "membership_plan")
public class MembershipPlan {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long memberPlanId;  
	 private String planName;
	 private String duration;
	 private String price;
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
