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

import com.api.ecommerce.dtos.BrandDTO;
import com.api.ecommerce.models.BrandModel;
import com.api.ecommerce.repositories.BrandRepository;
import com.api.ecommerce.services.exceptions.DatabaseException;
import com.api.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class BrandService {

    @Autowired
    private BrandRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<BrandDTO> findAllPaged(Pageable pageable) {
        Page<BrandModel> pageModels = categoryRepository.findAll(pageable);
        return pageModels.map(x -> new BrandDTO(x));
    }

    @Transactional(readOnly = true)
    public BrandDTO findById(Long id) {
        Optional<BrandModel> optional = categoryRepository.findById(id);
        BrandModel entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found: " + id));
        return new BrandDTO(entity);
    }

    @Transactional
    public BrandDTO insert(BrandDTO dto) {
        BrandModel entity = new BrandModel();
        copyDtoToEntity(dto, entity);
        entity = categoryRepository.save(entity);
        return new BrandDTO(entity);
    }

    @Transactional
    public BrandDTO update(BrandDTO dto, Long id) {
        try {
            BrandModel entity = categoryRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = categoryRepository.save(entity);
            return new BrandDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Entity not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(BrandDTO dto, BrandModel entity) {
        entity.setName(dto.getName());
    }
}
