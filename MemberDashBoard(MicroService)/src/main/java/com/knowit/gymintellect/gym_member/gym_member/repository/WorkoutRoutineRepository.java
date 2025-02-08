package com.knowit.gymintellect.gym_member.gym_member.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.gymintellect.gym_member.gym_member.entity.Member;
import com.knowit.gymintellect.gym_member.gym_member.entity.WorkoutRoutine;

public interface WorkoutRoutineRepository extends JpaRepository<WorkoutRoutine, Long> {
	
List<WorkoutRoutine> findByMemberAndCompleted(Member member, boolean completed);
    
    List<WorkoutRoutine> findByMember_MemberIdAndAssignedDate(Long memberId, LocalDate assignedDate);

    Optional<WorkoutRoutine> findByMember_MemberIdAndAssignedDateAndIsCustomRoutineTrue(Long memberId, LocalDate assignedDate);

    boolean existsByMember_MemberIdAndAssignedDate(Long memberId, LocalDate assignedDate);

}
