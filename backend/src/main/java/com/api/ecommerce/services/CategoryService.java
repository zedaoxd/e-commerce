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

import com.api.ecommerce.dtos.CategoryDTO;
import com.api.ecommerce.models.CategoryModel;
import com.api.ecommerce.repositories.CategoryRepository;
import com.api.ecommerce.services.exceptions.DatabaseException;
import com.api.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<CategoryModel> pageModels = categoryRepository.findAll(pageable);
        return pageModels.map(x -> new CategoryDTO(x));
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<CategoryModel> optional = categoryRepository.findById(id);
        CategoryModel entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found: " + id));
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        CategoryModel entity = new CategoryModel();
        copyDtoToEntity(dto, entity);
        entity = categoryRepository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(CategoryDTO dto, Long id) {
        try {
            CategoryModel entity = categoryRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = categoryRepository.save(entity);
            return new CategoryDTO(entity);
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

    private void copyDtoToEntity(CategoryDTO dto, CategoryModel entity) {
        entity.setName(dto.getName());
    }
}
