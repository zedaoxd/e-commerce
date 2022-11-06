package com.api.ecommerce.dtos;

import com.api.ecommerce.models.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Long id;

    @NotBlank(message = "cannot be blank")
    private String shortDescription;

    @NotBlank(message = "cannot be blank")
    private String longDescription;

    @NotNull(message = "cannot be null")
    private Double costValue;

    @NotNull(message = "cannot be null")
    private Double seleValue;

    @NotNull(message = "cannot be null")
    private Long brandId;

    @NotNull(message = "cannot be null")
    private Long categoryId;

    public ProductDTO(ProductModel entity) {
        this(entity.getId(),
                entity.getShortDescription(),
                entity.getLongDescription(),
                entity.getCostValue(),
                entity.getSeleValue(),
                entity.getBrand().getId(),
                entity.getCategory().getId());
    }
}
