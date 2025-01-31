package com.knowit.gymintellect.gym_intellect.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.knowit.gymintellect.gym_intellect.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Optional<User> findByResetToken(String resetToken);
    

//    List<User> findByRole(String role);
//
//    List<User> findByRoleAndIsVerified(String role, boolean isVerified);
    
 // Optional: Query for expired tokens
    @Query("SELECT u FROM User u WHERE u.tokenExpiration < :currentTime")
    List<User> findUsersWithExpiredTokens(@Param("currentTime") Instant currentTime);

    // Optional: Method for updating password by reset token
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.resetToken = :resetToken")
    int updatePasswordByResetToken(@Param("password") String password, @Param("resetToken") String resetToken);
}
