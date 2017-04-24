package com.tragent.pressing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tragent.pressing.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(@Param("name") String name);
	
}
