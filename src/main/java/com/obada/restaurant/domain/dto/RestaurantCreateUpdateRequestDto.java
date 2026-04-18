package com.obada.restaurant.domain.dto;


import com.obada.restaurant.domain.entity.Address;
import com.obada.restaurant.domain.entity.OperatingHours;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantCreateUpdateRequestDto {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "cuisine type is required")
    private String cuisineType;

    @NotBlank(message = "contactInformation type is required")
    private String contactInformation;

    @Valid
    private AddressDto address;

    @Valid
    private OperatingHoursDto operatingHours;

    @Size(min = 1, message = "at least one photo id is required")
    private List<String> photos;

}
