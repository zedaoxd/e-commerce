package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ecommerce.models.CategoryModel;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

}
