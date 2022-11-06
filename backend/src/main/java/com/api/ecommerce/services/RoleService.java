package com.api.ecommerce.services;

import com.api.ecommerce.dtos.RoleDTO;
import com.api.ecommerce.models.RoleModel;
import com.api.ecommerce.repositories.RoleRepository;
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
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<RoleDTO> findAllPaged(Pageable pageable) {
        Page<RoleModel> pageModels = roleRepository.findAll(pageable);
        return pageModels.map(x -> new RoleDTO(x));
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Optional<RoleModel> optional = roleRepository.findById(id);
        RoleModel entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found: " + id));
        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO insert(RoleDTO dto) {
        RoleModel entity = new RoleModel();
        copyDtoToEntity(dto, entity);
        entity = roleRepository.save(entity);
        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO update(RoleDTO dto, Long id) {
        try {
            RoleModel entity = roleRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = roleRepository.save(entity);
            return new RoleDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Entity not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(RoleDTO dto, RoleModel entity) {
        entity.setAuthority(dto.getAuthority());
    }
}
