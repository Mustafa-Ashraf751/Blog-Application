package com.BlogApplication.Bloging.application.api.entity.user;

import com.BlogApplication.Bloging.application.api.entity.audit.UserDataAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "geo")
public class Geo extends UserDataAudit {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String lng;
  private String lat;

  @OneToOne(mappedBy = "geo")
  private Address address;

  public Geo(String lng, String lat) {
    this.lng = lng;
    this.lat = lat;
  }

  @JsonIgnore
  @Override
  public Long getCreatedBy() {
    return super.getCreatedBy();
  }

  @JsonIgnore
  @Override
  public Long getUpdatedBy() {
    return super.getUpdatedBy();
  }

  @JsonIgnore
  @Override
  public void setCreatedBy(Long createdBy) {
    super.setCreatedBy(createdBy);
  }

  @JsonIgnore
  @Override
  public void setUpdatedBy(Long updatedBy) {
    super.setUpdatedBy(updatedBy);
  }

  @JsonIgnore
  @Override
  public Instant getCreatedAt() {
    return super.getCreatedAt();
  }

  @JsonIgnore
  @Override
  public Instant getUpdatedAt() {
    return super.getUpdatedAt();
  }

  @JsonIgnore
  @Override
  public void setCreatedAt(Instant createdAt) {
    super.setCreatedAt(createdAt);
  }

  @JsonIgnore
  @Override
  public void setUpdatedAt(Instant updatedAt) {
    super.setUpdatedAt(updatedAt);
  }
}
