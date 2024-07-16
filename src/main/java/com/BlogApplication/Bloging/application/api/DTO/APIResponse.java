package com.BlogApplication.Bloging.application.api.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonPropertyOrder({
        "success",
        "message"
})
public class APIResponse implements Serializable {

  @JsonProperty("message")
  private String message;

  @JsonProperty("success")
  private boolean success;

  @JsonIgnore
  private HttpStatus httpStatus;

  public APIResponse() {
  }

  public APIResponse(String message, boolean success) {
    this.message = message;
    this.success = success;
  }

  public APIResponse(String message, boolean success, HttpStatus httpStatus) {
    this.message = message;
    this.success = success;
    this.httpStatus = httpStatus;
  }
}
