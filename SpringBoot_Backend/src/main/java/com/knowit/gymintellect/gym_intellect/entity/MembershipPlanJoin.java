package com.knowit.gymintellect.gym_intellect.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "member_plan_join")
public class MembershipPlanJoin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;
    
    @ManyToOne
    @JoinColumn(name = "member_plan_id")
    private MembershipPlan membershipPlan;
    
    private Date startDate;
    private Date endDate;
    private String status;
    
    
	public Long getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
	}
	public MembershipPlan getMembershipPlan() {
		return membershipPlan;
	}
	public void setMembershipPlan(MembershipPlan membershipPlan) {
		this.membershipPlan = membershipPlan;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
