package com.KnowIt.Gym_intellect_Crud.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KnowIt.Gym_intellect_Crud.Entity.Member;
import com.KnowIt.Gym_intellect_Crud.Entity.WorkoutRoutine;



public interface WorkoutRoutineRepository extends JpaRepository<WorkoutRoutine, Long> {
	List<WorkoutRoutine> findByMember_MemberId(Long memberId);
}
