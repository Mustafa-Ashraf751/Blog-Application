package com.BlogApplication.Bloging.application.api.exception;

import com.BlogApplication.Bloging.application.api.DTO.APIResponse;

public class UnAuthorizedException extends RuntimeException {
  private APIResponse  apiResponse;

  public UnAuthorizedException(APIResponse apiResponse) {
    super();
    this.apiResponse = apiResponse;
  }
}
