package com.api.ecommerce.dtos;

import com.api.ecommerce.models.RoleModel;
import com.api.ecommerce.models.RolePersonModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePersonDTO {

    private Long id;
    private Long personId;
    private Long roleId;

    public RolePersonDTO(RolePersonModel model) {
        id = model.getId();
        personId = model.getPerson().getId();
        roleId = model.getRole().getId();
    }

}
