package com.BlogApplication.Bloging.application.api.entity.user;

import com.BlogApplication.Bloging.application.api.entity.audit.UserDataAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name ="address")
public class Address extends UserDataAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Pattern(regexp = "^[a-zA-Z]{5,}$",message = "please enter a valid name for city" )
  private String city;

  @Pattern(regexp = "^[a-zA-Z]{10,}$",message = "street Name must be in 10 character at least")
  private String street;

  @Pattern(regexp = "^[a-zA-Z]{10,}$",message = "suite location must be in 10 character at least")
  private String suite;

  @Pattern(regexp = "\\d{5}",message = "zipcode must be 5 digits number")
  private String zipCode;

  @OneToOne
  @JoinColumn(name = "geo_id")
  private Geo geo;

  @OneToOne(mappedBy = "address")
  private User user;

  public Address(String city, String street, String suite, String zipCode, Geo geo) {
    this.city = city;
    this.street = street;
    this.suite = suite;
    this.zipCode = zipCode;
    this.geo = geo;
  }


  @Override
  public Long getCreatedBy() {
    return super.getCreatedBy();
  }

  @Override
  public Long getUpdatedBy() {
    return super.getUpdatedBy();
  }

  @Override
  public void setCreatedBy(Long createdBy) {
    super.setCreatedBy(createdBy);
  }

  @Override
  public void setUpdatedBy(Long updatedBy) {
    super.setUpdatedBy(updatedBy);
  }

  @Override
  public Instant getCreatedAt() {
    return super.getCreatedAt();
  }

  @Override
  public Instant getUpdatedAt() {
    return super.getUpdatedAt();
  }

  @Override
  public void setCreatedAt(Instant createdAt) {
    super.setCreatedAt(createdAt);
  }

  @Override
  public void setUpdatedAt(Instant updatedAt) {
    super.setUpdatedAt(updatedAt);
  }


}
