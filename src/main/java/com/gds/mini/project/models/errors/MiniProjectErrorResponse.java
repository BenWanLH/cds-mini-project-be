package com.gds.mini.project.models.errors;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

public class MiniProjectErrorResponse implements ErrorResponse {
  private HttpStatusCode statusCode;

  private String errorCode;

  private String message;

  public MiniProjectErrorResponse(
    HttpStatusCode statusCode,
    String message,
    String errorCode) {
    this.statusCode = statusCode;
    this.message = message;
    this.errorCode = errorCode;
  }

  @Override
  public HttpStatusCode getStatusCode() {
    return statusCode;
  }

  @Override
  public ProblemDetail getBody() {
    return null;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
}
