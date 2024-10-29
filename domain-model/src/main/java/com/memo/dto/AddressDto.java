package com.memo.dto;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;

    @Size(max = 100, min = 2, message = "Address should have 2-100 characters long.")
    private String addressLine1;

    @Size(max = 100, message = "Address should have maximum 100 characters long.")
    private String addressLine2;

    @Size(max = 50, min = 2, message = "City should have 2-50 characters long.")
    private String city;

    @Size(max = 50, min = 2, message = "State should have 2-50 characters long.")
    private String state;

    @Size(max = 50, min = 2, message = "Country should have 2-50 characters long.")
    private String country;

    @Pattern(regexp = "^\\d{5}([-]|\\s*)?(\\d{4})?$", message = "Zipcode should have a valid form.")
    private String zipCode;

}
