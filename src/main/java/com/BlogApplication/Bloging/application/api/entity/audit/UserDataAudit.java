package com.BlogApplication.Bloging.application.api.entity.audit;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@JsonIgnoreProperties(value = {"createdBy","updatedBy"},allowGetters = true)
public abstract class UserDataAudit extends DateAudit {

  @CreatedBy
  @Column(updatable = false)
  private Long createdBy;

  @LastModifiedBy
  private Long updatedBy;


}
