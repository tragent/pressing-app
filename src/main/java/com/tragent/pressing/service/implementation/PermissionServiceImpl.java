package com.tragent.pressing.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tragent.pressing.model.Permission;
import com.tragent.pressing.repository.PermissionRepository;
import com.tragent.pressing.service.PermissionService;

@Service
@Secured("ROLE_ADMINISTRATION")
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Collection<Permission> findAll() {
		
		Collection<Permission> permissions = permissionRepository.findAll();
		return permissions;
	}

	@Override
	public Permission findById(Long id) {
		
		Permission permission = permissionRepository.findOne(id);
		return permission;
	}

	@Override
	public Permission findByName(String name) {
		
		Permission permission = permissionRepository.findByName(name);
		return permission;
	}

	@Override
	public Permission create(Permission permission) {
		
		Permission savedPermission = permissionRepository.save(permission);
		return savedPermission;
	}

	@Override
	public Permission update(Permission permission) {
		
		Permission updatedPermission = permissionRepository.save(permission);
		return updatedPermission;
	}

}
