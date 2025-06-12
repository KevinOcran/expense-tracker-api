package com.expensetracker.backend.repository;

import com.expensetracker.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
