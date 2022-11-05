package com.api.ecommerce.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api.ecommerce.models.CityModel;

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

    public CityDTO(CityModel entity) {
        this(entity.getId(), entity.getName(), entity.getState().getId());
    }
}
