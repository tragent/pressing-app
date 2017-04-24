package com.tragent.pressing.service.implementation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tragent.pressing.model.CleaningMaterial;
import com.tragent.pressing.repository.CleaningMaterialRepository;
import com.tragent.pressing.service.CleaningMaterialService;

@Service
@Secured("ROLE_MANAGEMENT")
public class CleaningMaterialServiceImpl implements CleaningMaterialService {
	
	@Autowired
	private CleaningMaterialRepository cleaningMaterialRepository;
	
	@Override
	public Collection<CleaningMaterial> findAll() {
		
		Collection<CleaningMaterial> cleaningMaterials;
		cleaningMaterials = cleaningMaterialRepository.findAll();
		return cleaningMaterials;
	}

	@Override
	public CleaningMaterial findById(Long id) {
		
		CleaningMaterial cleaningMaterial;
		cleaningMaterial = cleaningMaterialRepository.findOne(id);
		return cleaningMaterial;
	}

	@Override
	public CleaningMaterial findByName(String name) {

		CleaningMaterial cleaningMaterial;
		cleaningMaterial = cleaningMaterialRepository.findByName(name);
		return cleaningMaterial;
	}

	@Override
	public CleaningMaterial create(CleaningMaterial cleaningMaterial) {
		
		if (cleaningMaterialRepository.findByName(cleaningMaterial.getName()) == null) {
			CleaningMaterial savedCleaningMaterial;
			savedCleaningMaterial = cleaningMaterialRepository.save(cleaningMaterial);
			return savedCleaningMaterial;
		}
		
		return null;
	}

	@Override
	public CleaningMaterial update(CleaningMaterial cleaningMaterial) {

		CleaningMaterial savedCleaningMaterial;
		savedCleaningMaterial = cleaningMaterialRepository.save(cleaningMaterial);
		return savedCleaningMaterial;
	}

	@Override
	public void delete(Long id) {
		
		CleaningMaterial cleaningMaterial = findById(id);
		if (cleaningMaterial == null) {
			return;
		}
		
		cleaningMaterialRepository.delete(cleaningMaterial);
	}

}
