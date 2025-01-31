package com.knowit.gymintellect.gym_intellect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.gymintellect.gym_intellect.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
