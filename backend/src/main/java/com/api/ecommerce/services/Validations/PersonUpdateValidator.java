package com.api.ecommerce.services.Validations;

import com.api.ecommerce.controllers.exceptions.FieldMessage;
import com.api.ecommerce.dtos.PersonUpdateDTO;
import com.api.ecommerce.models.PersonModel;
import com.api.ecommerce.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonUpdateValidator implements ConstraintValidator<PersonUpdateValid, PersonUpdateDTO> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PersonRepository repository;

    @Override
    public void initialize(PersonUpdateValid ann) {
    }

    @Override
    public boolean isValid(PersonUpdateDTO dto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        PersonModel personModel = repository.findByEmail(dto.getEmail());
        if (personModel != null && personModel.getId() != userId) {
            list.add(new FieldMessage("email", "This email is already in use by another user"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
