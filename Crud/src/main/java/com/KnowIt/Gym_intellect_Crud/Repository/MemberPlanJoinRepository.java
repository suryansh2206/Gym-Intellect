package com.KnowIt.Gym_intellect_Crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KnowIt.Gym_intellect_Crud.Entity.MemberPlanJoin;

import java.util.List;

@Repository
public interface MemberPlanJoinRepository extends JpaRepository<MemberPlanJoin, Integer> {
    // Custom query methods can be added here if needed
    // Example: Find all joins by status
    List<MemberPlanJoin> findByStatus(String status);

    // Example: Find all joins for a specific membership plan
    List<MemberPlanJoin> findByMembershipPlan_MemberPlanId(int memberPlanId);
}