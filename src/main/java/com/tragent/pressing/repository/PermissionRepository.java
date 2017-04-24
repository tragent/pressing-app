package com.tragent.pressing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.pressing.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
	Permission findByName(@Param("name") String name);

}
