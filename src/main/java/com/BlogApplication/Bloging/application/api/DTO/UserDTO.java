package com.BlogApplication.Bloging.application.api.DTO;

import com.BlogApplication.Bloging.application.api.entity.user.Company;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Data
@NoArgsConstructor
public class UserDTO {
  @NotBlank
  @Size(max = 40,message = "The first name must be 40 characters at max")
  private String firstName;

  @NotBlank
  @Size(max = 40,message = "The last name must be 40 characters at max")
  private String lastName;

  @NotBlank
  @Size(max = 15,message = "The username max size must be 15")
  @Pattern(regexp = "^[a-zA-Z]{5,15}$",message = "The username must contains characters only")
  private String userName;

  @Email
  @NotBlank
  @NaturalId
  @Size(max = 40)
  private String email;

  @NotBlank
  @Size(max = 50)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  private String phone;
  private String website;
  private Company company;

  @NotBlank(message = "please enter your address")
  private AddressDTO addressDTO;


}
