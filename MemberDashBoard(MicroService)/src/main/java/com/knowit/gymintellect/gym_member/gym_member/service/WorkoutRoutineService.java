package com.knowit.gymintellect.gym_member.gym_member.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.knowit.gymintellect.gym_member.gym_member.entity.Member;
import com.knowit.gymintellect.gym_member.gym_member.entity.Workout;
import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutPlan;
import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutRoutine;
import com.knowit.gymintellect.gym_member.gym_member.repository.MemberRepository;
import com.knowit.gymintellect.gym_member.gym_member.repository.WorkoutPlanRepository;
import com.knowit.gymintellect.gym_member.gym_member.repository.WorkoutRepository;
import com.knowit.gymintellect.gym_member.gym_member.repository.WorkoutRoutineRepository;

@Service
public class WorkoutRoutineService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private WorkoutRoutineRepository workoutRoutineRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    // Assigns a workout to a member (Ensures it belongs to their assigned plan)
    public WorkoutRoutine assignWorkoutToMember(Long memberId, Long workoutId, LocalDate assignedDate, boolean isCustom) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        // Get the assigned workout plan
        WorkoutPlan assignedWorkoutPlan = member.getWorkoutPlan();
        if (assignedWorkoutPlan == null) {
            throw new RuntimeException("Member does not have an assigned workout plan");
        }

        // Check if the workout belongs to the member’s assigned plan
        if (!workoutPlanRepository.existsByPlanIdAndWorkouts_WorkoutId(assignedWorkoutPlan.getPlanId(), workoutId)) {
            throw new RuntimeException("Workout does not belong to the assigned workout plan");
        }

        // Prevent duplicate assignment
        if (workoutRoutineRepository.existsByMember_MemberIdAndAssignedDate(memberId, assignedDate)) {
            throw new RuntimeException("Workout already assigned for this date.");
        }

        WorkoutRoutine routine = new WorkoutRoutine();
        routine.setMember(member);
        routine.setWorkout(workout);
        routine.setWorkoutPlan(assignedWorkoutPlan);
        routine.setAssignedDate(assignedDate);
        routine.setPlannedSets(workout.getSets());
        routine.setPlannedReps(workout.getReps());
        routine.setCompletedSets(0);
        routine.setCompletedReps(0);
        routine.setCaloriesBurnt(0);
        routine.setCompletionTime(null);
        routine.setCustomRoutine(isCustom);

        return workoutRoutineRepository.save(routine);
    }

    // Automatically assigns workout routines for the next 6 days
    @Scheduled(cron = "0 0 2 * * SUN") // Runs every Sunday at 2 AM
    public void generateWeeklyWorkouts() {
        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            WorkoutPlan assignedPlan = member.getWorkoutPlan();

            if (assignedPlan != null) {
                List<Workout> workouts = workoutRepository.findByWorkoutPlanPlanId(assignedPlan.getPlanId());
                if (!workouts.isEmpty()) {
                    assignWorkoutsForWeek(member, workouts);
                }
            }
        }
    }

    private void assignWorkoutsForWeek(Member member, List<Workout> workouts) {
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 6; i++) { // Assign for 6 days
            LocalDate assignedDate = today.plusDays(i);
            Workout workout = workouts.get(i % workouts.size()); // Rotate through workouts

            assignWorkoutToMember(member.getMemberId(), workout.getWorkoutId(), assignedDate, false);
        }
    }

    // Mark workout as completed
    public WorkoutRoutine completeWorkout(Long routineId, int completedSets, int completedReps, float caloriesBurnt) {
        WorkoutRoutine routine = workoutRoutineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException("Workout routine not found"));

        routine.setCompletedSets(completedSets);
        routine.setCompletedReps(completedReps);
        routine.setCaloriesBurnt(caloriesBurnt);
        routine.setCompletionTime(LocalDateTime.now());
        routine.setCompleted(true);

        return workoutRoutineRepository.save(routine);
    }

    // Get all pending workout routines
    public List<WorkoutRoutine> getPendingWorkouts(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return workoutRoutineRepository.findByMemberAndCompleted(member, false);
    }

    // Get all completed workout routines
    public List<WorkoutRoutine> getCompletedWorkouts(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return workoutRoutineRepository.findByMemberAndCompleted(member, true);
    }
}
