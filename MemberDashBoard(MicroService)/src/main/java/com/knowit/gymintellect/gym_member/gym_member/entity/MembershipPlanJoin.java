package com.knowit.gymintellect.gym_member.gym_member.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "member_plan_join")
public class MembershipPlanJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "membership_id")
    private Long membershipId;  // Primary Key

    @ManyToOne
    @JoinColumn(name = "member_plan_id", referencedColumnName = "memberPlanId", nullable = false)
    @JsonIgnore
    private MembershipPlan membershipPlan;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "memberId", nullable = false)
    @JsonIgnore
    private Member member;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(nullable = false, length = 20)
    private String status; // ACTIVE, EXPIRED

    public MembershipPlanJoin() {}

    // Method to check if membership is active
    public boolean isMembershipActive() {
        Date today = new Date();
        return "ACTIVE".equalsIgnoreCase(this.status) && today.before(this.endDate);
    }
    
    public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
    
    
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

