package com.tragent.pressing.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.pressing.model.MaterialPurchase;

public interface MaterialPurchaseRepository extends JpaRepository<MaterialPurchase, Long> {

	Collection<MaterialPurchase> findByPurchasedDate(@Param("date") Date date);
	Collection<MaterialPurchase> findByCleaningMaterial(@Param("id") Long id);
	
}