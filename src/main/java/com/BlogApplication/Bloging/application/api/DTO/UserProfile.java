package com.BlogApplication.Bloging.application.api.DTO;

import com.BlogApplication.Bloging.application.api.entity.user.Address;
import com.BlogApplication.Bloging.application.api.entity.user.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private Instant joinedAt;
  private String email;
  private Address address;
  private String phone;
  private String webSite;
  private Company company;
  private Long postCount;


}
