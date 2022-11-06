package com.api.ecommerce.services;

import com.api.ecommerce.dtos.PersonDTO;
import com.api.ecommerce.dtos.PersonInsertDTO;
import com.api.ecommerce.models.PersonModel;
import com.api.ecommerce.models.RolePersonModel;
import com.api.ecommerce.repositories.CityRepository;
import com.api.ecommerce.repositories.PersonRepository;
import com.api.ecommerce.repositories.RoleRepository;
import com.api.ecommerce.services.exceptions.DatabaseException;
import com.api.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<PersonDTO> findAllPaged(Pageable pageable) {
        Page<PersonModel> pageModels = personRepository.findAll(pageable);
        return pageModels.map(PersonDTO::new);
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) {
        Optional<PersonModel> optional = personRepository.findById(id);
        PersonModel entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found: " + id));
        return new PersonDTO(entity, entity.getRolePersons());
    }

    @Transactional
    public PersonDTO insert(PersonInsertDTO dto) {
        PersonModel entity = new PersonModel();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = personRepository.save(entity);
        return new PersonDTO(entity, entity.getRolePersons());
    }

    @Transactional
    public PersonDTO update(PersonDTO dto, Long id) {
        try {
            PersonModel entity = personRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = personRepository.save(entity);
            return new PersonDTO(entity, entity.getRolePersons());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            personRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Entity not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(PersonDTO dto, PersonModel entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setStreet(dto.getStreet());
        entity.setCep(dto.getCep());
        entity.setCity(cityRepository.getReferenceById(dto.getCityId()));

        var rolesPersons = dto.getRolePersons().stream().map(role -> {
            RolePersonModel rolePersonModel = new RolePersonModel();
            rolePersonModel.setRole(roleRepository.getReferenceById(role.getRoleId()));
            return rolePersonModel;
        }).collect(Collectors.toList());
        entity.setRolePersons(rolesPersons);
    }
}
