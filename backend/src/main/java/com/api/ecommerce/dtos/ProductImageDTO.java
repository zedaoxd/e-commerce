package com.api.ecommerce.dtos;

import com.api.ecommerce.models.ProductImageModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {

    private Long id;

    private String name;

    private Long productId;

    public ProductImageDTO(ProductImageModel model) {
        this(model.getId(), model.getName(), model.getProduct().getId());
    }
}
