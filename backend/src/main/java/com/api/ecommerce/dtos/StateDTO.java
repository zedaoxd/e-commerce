package com.api.ecommerce.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    public StateDTO(StateModel entity) {
        this(entity.getId(), entity.getName(), entity.getInitials());
    }
}