package com.api.ecommerce.dtos;

import com.api.ecommerce.services.Validations.PersonInsertValid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@PersonInsertValid
public class PersonInsertDTO extends PersonDTO {

    private String password;
}
