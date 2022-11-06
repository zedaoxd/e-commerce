package com.api.ecommerce.dtos;

import com.api.ecommerce.models.RoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {

    private Long id;

    @Size(max = 30, message = "Must be 30 characters or less")
    @NotBlank(message = "cannot be blank")
    private String authority;

    public RoleDTO(RoleModel entity) {
        this(entity.getId(), entity.getAuthority());
    }
}
