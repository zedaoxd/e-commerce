package com.api.ecommerce.services;

import com.api.ecommerce.dtos.ProductImageDTO;
import com.api.ecommerce.models.ProductImageModel;
import com.api.ecommerce.models.ProductModel;
import com.api.ecommerce.repositories.ProductImageRepository;
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
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Optional;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final String PATH_IMAGE = "src/main/resources/images/";

    @Transactional
    public ProductImageDTO insert(Long productId, MultipartFile file) {
        try {
            Optional<ProductModel> obj = productRepository.findById(productId);
            ProductModel product = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
            ProductImageModel entity = new ProductImageModel();
            entity.setProduct(product);

            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String nameImage = Instant.now().getNano() + file.getOriginalFilename();
                Path path = Paths.get(PATH_IMAGE + nameImage);
                Files.write(path, bytes);
                entity.setName(nameImage);
            }
            entity = productImageRepository.save(entity);
            return new ProductImageDTO(entity);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
