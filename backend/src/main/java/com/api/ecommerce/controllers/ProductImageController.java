package com.api.ecommerce.controllers;

import com.api.ecommerce.dtos.ProductImageDTO;
import com.api.ecommerce.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/product-image")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @PostMapping
    public ResponseEntity<ProductImageDTO> insert(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {
        ProductImageDTO dto = productImageService.insert(id, file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}