package com.knowit.gymintellect.gym_member.gym_member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.gymintellect.gym_member.gym_member.entity.Member;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByUserUserId(int user_id);
	Optional<Member> findByMemberId(Long member_id);
}