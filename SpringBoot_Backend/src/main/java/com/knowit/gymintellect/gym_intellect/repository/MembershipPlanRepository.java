package com.knowit.gymintellect.gym_intellect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.gymintellect.gym_intellect.entity.MembershipPlan;

public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, Long> {

	List<MembershipPlan> findByGymProfile_GymProfileId(Long gymProfileId);
}

