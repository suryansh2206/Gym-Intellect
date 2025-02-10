package com.KnowIt.Gym_intellect_Crud.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KnowIt.Gym_intellect_Crud.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByRole_Rid(int rid);
	Optional<User> findByuserId(Long userId);
}
