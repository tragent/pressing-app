package com.tragent.pressing.service.implementation;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tragent.pressing.model.MaterialPurchase;
import com.tragent.pressing.repository.MaterialPurchaseRepository;
import com.tragent.pressing.service.MaterialPurchaseService;

@Service
@Secured({"ROLE_MANAGEMENT", "ROLE_ADMINISTRATION"})
public class MaterialPurchaseServiceImpl implements MaterialPurchaseService {
	
	@Autowired
	private MaterialPurchaseRepository materialpurchaserepository;

	@Override
	public Collection<MaterialPurchase> findAll() {
		
		Collection<MaterialPurchase> materialPurchases = materialpurchaserepository.findAll() ;
		return materialPurchases;
	}

	@Override
	public MaterialPurchase findById(Long id) {
		
		MaterialPurchase materialPurchase = materialpurchaserepository.findOne(id);
		return materialPurchase;
	}

	@Override
	public Collection<MaterialPurchase> findByPurchasDate(Date purchaseDate) {
		
		Collection<MaterialPurchase> materialPurchases = materialpurchaserepository.findByPurchasedDate(purchaseDate);
		return materialPurchases;
	}

	@Override
	public Collection<MaterialPurchase> findByCleaningMaterial(Long id) {
		
		Collection<MaterialPurchase> materialPurchases = materialpurchaserepository.findByCleaningMaterial(id);
		return materialPurchases;
	}

	@Override
	public MaterialPurchase create(MaterialPurchase purchase) {
		
		MaterialPurchase savedPurchase = materialpurchaserepository.save(purchase);
		return savedPurchase;
	}

	@Override
	public MaterialPurchase update(MaterialPurchase purchase) {
		
		MaterialPurchase updatedPurchase = materialpurchaserepository.save(purchase);
		return updatedPurchase;
	}

}
