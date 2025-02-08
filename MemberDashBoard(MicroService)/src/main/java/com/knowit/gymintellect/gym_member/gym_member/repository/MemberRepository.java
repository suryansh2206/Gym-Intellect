package com.knowit.gymintellect.gym_member.gym_member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.gymintellect.gym_member.gym_member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Member findByUserUserId(Long userId);
	
}
