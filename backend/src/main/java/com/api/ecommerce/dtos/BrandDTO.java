package com.api.ecommerce.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.api.ecommerce.models.BrandModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {

    private Long id;

    @Size(min = 5, max = 50, message = "Must be between 5 and 50 characters")
    @NotBlank(message = "Cannot blank")
    private String name;

    public BrandDTO(BrandModel entity) {
        this(entity.getId(), entity.getName());
    }
}
