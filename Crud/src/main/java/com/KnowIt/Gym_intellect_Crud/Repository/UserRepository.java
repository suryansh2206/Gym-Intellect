package com.KnowIt.Gym_intellect_Crud.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KnowIt.Gym_intellect_Crud.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByRole_Rid(int rid);
	Optional<User> findByuserId(Long userId);
}
