package com.api.ecommerce.controllers.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage implements Serializable {

    private String fieldName;
    private String message;
}
