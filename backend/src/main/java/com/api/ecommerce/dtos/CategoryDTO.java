package com.api.ecommerce.dtos;

import java.time.Instant;

import com.api.ecommerce.models.CategoryModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private Instant createDate;
    private Instant updateDate;

    public CategoryDTO(CategoryModel entity) {
        this(entity.getId(), entity.getName(), entity.getCreateDate(), entity.getUpdateDate());
    }
}
