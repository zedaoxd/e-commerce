package com.api.ecommerce.dtos;

import com.api.ecommerce.models.BrandModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {

    private Long id;
    private String name;

    public BrandDTO(BrandModel entity) {
        this(entity.getId(), entity.getName());
    }
}
