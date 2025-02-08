package com.knowit.gymintellect.gym_member.gym_member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GymMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymMemberApplication.class, args);
	}

}
