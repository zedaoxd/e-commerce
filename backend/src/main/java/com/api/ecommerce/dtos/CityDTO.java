package com.api.ecommerce.dtos;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.ecommerce.models.CityModel;
import com.api.ecommerce.models.PersonModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private Long id;

    @Size(min = 2, max = 100, message = "Must be between 5 and 100 characters")
    @NotBlank(message = "Cannot blank")
    private String name;

    @NotNull(message = "cannot be null")
    private Long stateId;

    private Set<PersonDTO> persons = new HashSet<>();

    public CityDTO(CityModel entity) {
        id = entity.getId();
        name = entity.getName();
        stateId = entity.getState().getId();
    }

    public CityDTO(CityModel entity, Set<PersonModel> persons) {
        id = entity.getId();
        name = entity.getName();
        stateId = entity.getState().getId();
        persons.forEach(x -> this.persons.add(new PersonDTO(x)));
    }
}
