package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ecommerce.models.BrandModel;

@Repository
public interface BrandRepository extends JpaRepository<BrandModel, Long> {

}
