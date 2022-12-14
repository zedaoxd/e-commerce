package com.api.ecommerce.dtos;

import com.api.ecommerce.models.PersonModel;
import com.api.ecommerce.models.RolePersonModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    private List<RolePersonDTO> rolePersons = new ArrayList<>();

    public PersonDTO(PersonModel entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.street = entity.getStreet();
        this.cep = entity.getCep();
        this.cityId = entity.getCity().getId();
    }

    public PersonDTO(PersonModel entity, List<RolePersonModel> rolePersons) {
        this(entity);
        List<RolePersonDTO> l = rolePersons.stream().map(RolePersonDTO::new).toList();
        this.rolePersons.clear();
        this.rolePersons.addAll(l);
    }
}
