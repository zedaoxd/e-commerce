package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ecommerce.models.StateModel;

@Repository
public interface StateRepository extends JpaRepository<StateModel, Long> {

}