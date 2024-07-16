package com.BlogApplication.Bloging.application.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDTO {

  @Pattern(regexp = "^[a-zA-Z]{5,}$",message = "please enter a valid name for city" )
  private String city;

  @Pattern(regexp = "^[a-zA-Z]{10,}$",message = "street Name must be in 10 character at least")
  private String street;

  @Pattern(regexp = "^[a-zA-Z]{10,}$",message = "suite location must be in 10 character at least")
  private String suite;

  @Pattern(regexp = "\\d{5}",message = "zipcode must be 5 digits number")
  private String zipCode;

  @NotBlank(message = "please enter your geo")
  private GeoDTO geoDTO;
}
