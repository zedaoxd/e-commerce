package com.api.ecommerce.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.api.ecommerce.dtos.PersonUpdateDTO;
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

import com.api.ecommerce.dtos.PersonDTO;
import com.api.ecommerce.dtos.PersonInsertDTO;
import com.api.ecommerce.services.PersonService;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<Page<PersonDTO>> findAllPaged(
            @PageableDefault(page = 0, size = 12, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(personService.findAllPaged(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> insert(@Valid @RequestBody PersonInsertDTO dto) {
        PersonDTO personDTO = personService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(personDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> update(@Valid @RequestBody PersonUpdateDTO dto, @PathVariable("id") Long id) {
        PersonDTO personDto = personService.update(dto, id);
        return ResponseEntity.ok(personDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
