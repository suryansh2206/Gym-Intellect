package com.knowit.gymintellect.gym_member.gym_member.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_member.gym_member.entity.Member;
import com.knowit.gymintellect.gym_member.gym_member.entity.User;
import com.knowit.gymintellect.gym_member.gym_member.repository.MemberRepository;
import com.knowit.gymintellect.gym_member.gym_member.repository.UserRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Optional<Map<String, Object>> getMemberDetails(int memberId) {
        
		Optional<Member> memberopt = memberRepository.findByUserUserId(memberId);
		Optional<User> user = userRepository.findByUserId(memberId);
		if(memberopt.isEmpty()) {
			return Optional.empty();
		}
		
		Member member = memberopt.get();
		
		Map<String,Object> response = new HashMap<String, Object>();
		response.put("username", member.getUser().getUsername());
		response.put("dob", member.getDob());
        response.put("gender", member.getGender());
        response.put("address", member.getAddress());
//        response.put("membershipId", member.getMembership().getMembershipId());
        response.put("height", member.getHeight());
        
		return Optional.of(response);
    }
}
