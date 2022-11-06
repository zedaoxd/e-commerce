package com.api.ecommerce.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.ecommerce.dtos.CityDTO;
import com.api.ecommerce.models.CityModel;
import com.api.ecommerce.repositories.CityRepository;
import com.api.ecommerce.repositories.StateRepository;
import com.api.ecommerce.services.exceptions.DatabaseException;
import com.api.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Transactional(readOnly = true)
    public Page<CityDTO> findAllPaged(Pageable pageable) {
        Page<CityModel> pageModels = cityRepository.findAll(pageable);
        return pageModels.map(x -> new CityDTO(x));
    }

    @Transactional(readOnly = true)
    public CityDTO findById(Long id) {
        Optional<CityModel> optional = cityRepository.findById(id);
        CityModel entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found: " + id));
        return new CityDTO(entity, entity.getPersons());
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        CityModel entity = new CityModel();
        copyDtoToEntity(dto, entity);
        entity = cityRepository.save(entity);
        return new CityDTO(entity);
    }

    @Transactional
    public CityDTO update(CityDTO dto, Long id) {
        try {
            CityModel entity = cityRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = cityRepository.save(entity);
            return new CityDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Entity not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(CityDTO dto, CityModel entity) {
        entity.setName(dto.getName());
        entity.setState(stateRepository.getReferenceById(dto.getStateId()));
    }

}
