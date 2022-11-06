package com.api.ecommerce.services;

import com.api.ecommerce.dtos.ProductDTO;
import com.api.ecommerce.models.ProductModel;
import com.api.ecommerce.repositories.BrandRepository;
import com.api.ecommerce.repositories.CategoryRepository;
import com.api.ecommerce.repositories.ProductRepository;
import com.api.ecommerce.services.exceptions.DatabaseException;
import com.api.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<ProductModel> pageModels = productRepository.findAll(pageable);
        return pageModels.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<ProductModel> optional = productRepository.findById(id);
        ProductModel entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found: " + id));
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        ProductModel entity = new ProductModel();
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(ProductDTO dto, Long id) {
        try {
            ProductModel entity = productRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Entity not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, ProductModel entity) {
        entity.setCostValue(dto.getCostValue());
        entity.setSeleValue(dto.getSeleValue());
        entity.setShortDescription(dto.getShortDescription());
        entity.setLongDescription(dto.getLongDescription());
        entity.setBrand(brandRepository.getReferenceById(dto.getBrandId()));
        entity.setCategory(categoryRepository.getReferenceById(dto.getCategoryId()));
    }

}
