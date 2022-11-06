package com.api.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ecommerce.models.PersonModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {

    boolean existsPersonModelByEmail(String email);

    PersonModel findByEmail(String email);
}
