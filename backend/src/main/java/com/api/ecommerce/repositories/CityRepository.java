package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ecommerce.models.CityModel;

@Repository
public interface CityRepository extends JpaRepository<CityModel, Long> {

}
