package com.api.ecommerce.repositories;

import com.api.ecommerce.models.ProductImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageModel, Long> {
}
