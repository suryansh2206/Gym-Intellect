package com.knowit.gymintellect.gym_intellect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.knowit.gymintellect.gym_intellect.entity.Member;
import com.knowit.gymintellect.gym_intellect.repository.MemberRepository;

public class MemberService {
	@Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembersWithDetails() {
        return memberRepository.findAll();
    }
}
