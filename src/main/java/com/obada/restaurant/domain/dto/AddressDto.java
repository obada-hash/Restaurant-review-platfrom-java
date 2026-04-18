package com.obada.restaurant.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {


    @NotBlank(message = "street number must be provided")
    @Pattern(regexp = "^[0-9]{1,5}[a-zA-Z]?&", message = "wrong formating")
    private String streetNumber;

    @NotBlank(message = "street name must be provided")
    private String streetName;

    private String unit;

    @NotBlank(message = "city must be provided")
    private String city;

    @NotBlank(message = "postal must be provided")
    private String postalCode;

    @NotBlank(message = "country must be provided")
    private String country;

}
