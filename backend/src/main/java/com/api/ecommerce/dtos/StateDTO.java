package com.api.ecommerce.dtos;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.api.ecommerce.models.CityModel;
import com.api.ecommerce.models.StateModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateDTO {

    private Long id;

    @Size(min = 5, max = 80, message = "Must be between 5 and 80 characters")
    @NotBlank(message = "Cannot blank")
    private String name;

    @Size(min = 2, max = 2, message = "Must be 2 characters")
    @NotBlank(message = "Cannot blank")
    private String initials;

    private Set<CityDTO> cities = new HashSet<>();

    public StateDTO(StateModel entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.initials = entity.getInitials();
    }

    public StateDTO(StateModel entity, Set<CityModel> cities) {
        this(entity);
        cities.forEach(x -> this.cities.add(new CityDTO(x)));
    }
}