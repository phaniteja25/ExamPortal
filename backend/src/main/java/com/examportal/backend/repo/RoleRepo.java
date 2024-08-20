package com.examportal.backend.repo;

import com.examportal.backend.RoleName;
import com.examportal.backend.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Roles,Integer> {

    Roles findByRolename(RoleName Rolename);

}
