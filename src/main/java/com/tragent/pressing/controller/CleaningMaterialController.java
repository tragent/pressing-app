package com.tragent.pressing.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tragent.pressing.model.CleaningMaterial;
import com.tragent.pressing.service.CleaningMaterialService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("api/v1/materials")
public class CleaningMaterialController {
	
	@Autowired
	private CleaningMaterialService cleaningMaterialService;
	
	/**
	 * Get all cleaning materials or cleaning material with a given name.
	 * 
	 * @param materialName
	 * @return Collection of cleaning materials or material with the given name
	 */
	@RequestMapping(method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<CleaningMaterial>> getCleaningMaterials(@RequestParam(value = "materialName", required = false) String materialName){
		
		Collection<CleaningMaterial> materials = new ArrayList<>();
		if (materialName != null) {
			CleaningMaterial material = cleaningMaterialService.findByName(materialName);
			materials.add(material);
			
		} else {
			Collection<CleaningMaterial> allMaterials = cleaningMaterialService.findAll();
			materials.addAll(allMaterials);
		}
		
		return new ResponseEntity<>(materials, HttpStatus.OK);
	}
	
	/**
	 * Get cleaning material with given material id.
	 * 
	 * @param materialId
	 * @return CleaningMaterial object or 404 if material is not found
	 */
	@RequestMapping(value="/{materialId}",
			method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CleaningMaterial> getCleaningMaterialById(@PathVariable("materialId") Long materialId){
		
		CleaningMaterial material = cleaningMaterialService.findById(materialId);
		if (material == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(material, HttpStatus.OK);
	}
	
	/**
	 * Create new cleaning material.
	 * 
	 * @param material
	 * @return CleaningMaterial object (created object)
	 */
	@RequestMapping(method=RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CleaningMaterial> createCleaningMaterial(@RequestBody CleaningMaterial material){
		
		material = cleaningMaterialService.create(material);
		if (material == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(material, HttpStatus.CREATED);
	}
	
	/**
	 * Update cleaning material.
	 * 
	 * @param material
	 * @return CleaningMaterial object (updated object).
	 */
	@RequestMapping(value="/{materialId}",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CleaningMaterial> updateCleaningMaterial(@RequestBody CleaningMaterial material){
		
		material = cleaningMaterialService.update(material);
		if (material == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(material, HttpStatus.OK);
	}
	
}
