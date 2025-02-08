package com.KnowIt.Gym_intellect_Crud.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "membership_plan")
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_plan_id")
    private int memberPlanId;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "gym_profile_id")
    private Integer gymProfileId;

    @OneToMany(mappedBy = "membershipPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MemberPlanJoin> memberPlanJoins;

    // Getters and Setters
    public int getMemberPlanId() {
        return memberPlanId;
    }

    public void setMemberPlanId(int memberPlanId) {
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

    public Integer getGymProfileId() {
        return gymProfileId;
    }

    public void setGymProfileId(Integer gymProfileId) {
        this.gymProfileId = gymProfileId;
    }

    public List<MemberPlanJoin> getMemberPlanJoins() {
        return memberPlanJoins;
    }

    public void setMemberPlanJoins(List<MemberPlanJoin> memberPlanJoins) {
        this.memberPlanJoins = memberPlanJoins;
    }
}