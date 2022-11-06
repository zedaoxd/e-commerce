package com.api.ecommerce.controllers;

import com.api.ecommerce.dtos.ProductDTO;
import com.api.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAllPaged(
            @PageableDefault(page = 0, size = 12, sort = "seleValue", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(productService.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        dto = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto, @PathVariable("id") Long id) {
        dto = productService.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
