package com.api.ecommerce.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.api.ecommerce.models.PersonModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO implements Serializable {

    private Long id;

    @Size(min = 5, max = 100, message = "Must be between 5 and 100 characters")
    @NotBlank(message = "cannot be blank")
    private String name;

    @CPF(message = "cpf is not valid")
    private String cpf;

    @Email(message = "email is not valid")
    private String email;

    @Size(max = 80, message = "must be 80 characters or less")
    @NotBlank(message = "cannot be blank")
    private String street;

    @Size(min = 8, max = 8, message = "must be 8 characters")
    @NotBlank(message = "cannot be blank")
    private String cep;

    @NotNull(message = "cannot be NULL")
    private Long cityId;

    public PersonDTO(PersonModel entity) {
        this(entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getStreet(),
                entity.getCep(),
                entity.getCity().getId());
    }
}
