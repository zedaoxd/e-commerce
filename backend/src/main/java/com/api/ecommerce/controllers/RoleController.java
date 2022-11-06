package com.api.ecommerce.controllers;

import com.api.ecommerce.dtos.RoleDTO;
import com.api.ecommerce.services.RoleService;
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
@RequestMapping(value = "/api/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<Page<RoleDTO>> findAllPaged(Pageable pageable) {
        return ResponseEntity.ok(roleService.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> insert(@Valid @RequestBody RoleDTO dto) {
        dto = roleService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO dto, @PathVariable("id") Long id) {
        dto = roleService.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
