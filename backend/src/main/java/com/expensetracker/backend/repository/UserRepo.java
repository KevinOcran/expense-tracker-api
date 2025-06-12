package com.expensetracker.backend.repository;

import com.expensetracker.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);  // For registration validation
}
