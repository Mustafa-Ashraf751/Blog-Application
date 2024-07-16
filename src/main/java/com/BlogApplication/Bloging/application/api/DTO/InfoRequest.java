package com.BlogApplication.Bloging.application.api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InfoRequest {

  @NotBlank
  private String street;

  @NotBlank
  private String city;

  @NotBlank
  private String suite;

  @NotBlank
  private String zipCode;

  private String companyName;

  private String catchPhrase;

  private String bs;

  private String website;

  private String phone;

  private String lat;

  private String lng;

}
