package com.example.golbackend.modules.auth.repositories;

import java.util.Optional;

import com.example.golbackend.modules.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository <Role, Long> {

    Optional<Role> findByRoleName(String roleName);

}
