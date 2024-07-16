package com.BlogApplication.Bloging.application.api.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSummary {
  private Long id;
  private String userName;
  private String lastName;
  private String username;
}
