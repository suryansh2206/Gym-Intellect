//package com.knowit.gymintellect.gym_member.gym_member.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.knowit.gymintellect.gym_member.gym_member.entity.DietMeal;
//import com.knowit.gymintellect.gym_member.gym_member.entity.DietPlan;
//import com.knowit.gymintellect.gym_member.gym_member.entity.Member;
//import com.knowit.gymintellect.gym_member.gym_member.repository.DietMealRepository;
//import com.knowit.gymintellect.gym_member.gym_member.repository.DietPlanRepository;
//import com.knowit.gymintellect.gym_member.gym_member.repository.MemberRepository;
//
//@Service
//public class DietPlanService {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private DietPlanRepository dietPlanRepository;
//
//    @Autowired
//    private DietMealRepository dietMealRepository;
//
//    public DietPlan getDietPlanForMember(Long userId) {
//        Member member = memberRepository.findByUser_UserId(userId);
//        if (member != null && member.getMembership().getStatus().equalsIgnoreCase("ACTIVE")) {
//            DietPlan dietPlan = dietPlanRepository.findByWorkoutPlanPlanId(member.getWorkoutPlan().getPlanId());
//            List<DietMeal> meals = dietMealRepository.findByDietPlanDietPlanId(dietPlan.getDietPlanId());
//            dietPlan.setMeals(meals);
//            return dietPlan;
//        }
//        return null;
//    }
//}
