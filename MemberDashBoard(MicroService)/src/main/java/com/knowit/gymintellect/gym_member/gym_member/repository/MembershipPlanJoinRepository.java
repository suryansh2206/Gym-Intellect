package com.knowit.gymintellect.gym_member.gym_member.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.gymintellect.gym_member.gym_member.entity.MembershipPlanJoin;

@Repository
public interface MembershipPlanJoinRepository extends JpaRepository<MembershipPlanJoin, Long> {
	Optional<MembershipPlanJoin> findByMemberMemberId(Long memberId);
	List<MembershipPlanJoin> findByEndDateBeforeAndStatus(Date date, String status);
}
