package com.BlogApplication.Bloging.application.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
  private String resource;
  private String fieldName;
  private String field;
  private Long fieldId;


  public ResourceNotFoundException() {
    super();
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String resource, String fieldName, String field) {
    super(String.format("%s is not found with %s %s",resource,field,fieldName));
    this.resource = resource;
    this.fieldName = fieldName;
    this.field = field;
  }

  public ResourceNotFoundException(String resource, String field, Long fieldId) {
    super(String.format("%s is not found with %s :%d",resource,field,fieldId));
    this.resource = resource;
    this.field = field;
    this.fieldId = fieldId;
  }
}
