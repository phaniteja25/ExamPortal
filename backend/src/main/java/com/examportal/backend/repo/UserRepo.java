package com.examportal.backend.repo;

import com.examportal.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<Users, UUID> {

    public Users findByUsername(String username);
}
