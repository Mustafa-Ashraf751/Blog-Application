package com.BlogApplication.Bloging.application.api.exception;

import com.BlogApplication.Bloging.application.api.DTO.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

  private APIResponse apiResponse;

  public BadRequestException() {
    super();
  }

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(APIResponse apiResponse){
    super();
    this.apiResponse = apiResponse;
  }

  public APIResponse getApiResponse() {
    return apiResponse;
  }
}
