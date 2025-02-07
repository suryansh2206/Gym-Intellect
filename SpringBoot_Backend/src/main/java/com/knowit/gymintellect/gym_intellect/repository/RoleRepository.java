package com.knowit.gymintellect.gym_intellect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.gymintellect.gym_intellect.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);  // Fetch role by ID
    public Optional<Role> findByName(String name);
}

