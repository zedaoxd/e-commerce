package com.api.ecommerce.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.ecommerce.dtos.CityDTO;
import com.api.ecommerce.services.CityService;

@RestController
@RequestMapping(value = "/api/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<Page<CityDTO>> findAllPaged(
            @PageableDefault(page = 0, size = 12, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(cityService.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cityService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CityDTO> insert(@Valid @RequestBody CityDTO dto) {
        dto = cityService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CityDTO> update(@Valid @RequestBody CityDTO dto, @PathVariable("id") Long id) {
        dto = cityService.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
