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

import com.api.ecommerce.dtos.StateDTO;
import com.api.ecommerce.models.StateModel;
import com.api.ecommerce.repositories.StateRepository;
import com.api.ecommerce.services.exceptions.DatabaseException;
import com.api.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Transactional(readOnly = true)
    public Page<StateDTO> findAllPaged(Pageable pageable) {
        Page<StateModel> pageModels = stateRepository.findAll(pageable);
        return pageModels.map(x -> new StateDTO(x));
    }

    @Transactional(readOnly = true)
    public StateDTO findById(Long id) {
        Optional<StateModel> optional = stateRepository.findById(id);
        StateModel entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found: " + id));
        return new StateDTO(entity);
    }

    @Transactional
    public StateDTO insert(StateDTO dto) {
        StateModel entity = new StateModel();
        copyDtoToEntity(dto, entity);
        entity = stateRepository.save(entity);
        return new StateDTO(entity);
    }

    @Transactional
    public StateDTO update(StateDTO dto, Long id) {
        try {
            StateModel entity = stateRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = stateRepository.save(entity);
            return new StateDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            stateRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Entity not found: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(StateDTO dto, StateModel entity) {
        entity.setName(dto.getName());
        entity.setInitials(dto.getInitials());
    }
}