package com.api.ecommerce.services.Validations;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.ecommerce.controllers.exceptions.FieldMessage;
import com.api.ecommerce.dtos.PersonInsertDTO;
import com.api.ecommerce.repositories.PersonRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class PersonInsertValidator implements ConstraintValidator<PersonInsertValid, PersonInsertDTO> {

    @Autowired
    private PersonRepository repository;

    @Override
    public void initialize(PersonInsertValid ann) {
    }

    @Override
    public boolean isValid(PersonInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (repository.existsPersonModelByEmail(dto.getEmail())) {
            list.add(new FieldMessage("email", "this email already exists"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
