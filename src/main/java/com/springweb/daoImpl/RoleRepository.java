package com.springweb.daoImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springweb.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	Role findByRoleName(String roleName);
}
