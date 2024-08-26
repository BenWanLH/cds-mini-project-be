package com.gds.mini.project.utils.controller.advice;

import com.gds.mini.project.models.enums.Error;
import com.gds.mini.project.models.errors.MiniProjectErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class CustomControllerAdvice {
  @ExceptionHandler(Exception.class) // exception handled
  public ResponseEntity<ErrorResponse> handleExceptions(
    Exception e
  ) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    String errorCode = e.getMessage();

    Error errorEnum = Error.getErrorByErrorCode(errorCode);

    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    e.printStackTrace(printWriter);
    String stackTrace = stringWriter.toString();

    System.out.println(stackTrace);

    if (errorEnum != null) {
      return new ResponseEntity<>(new MiniProjectErrorResponse(
        errorEnum.httpStatus,
        errorEnum.errorMessage,
        errorEnum.errorCode
      ),
        errorEnum.httpStatus);
    } else {
      return new ResponseEntity<>(new MiniProjectErrorResponse(
        status,
        e.getMessage(),
        "0"
      ),
        status);
    }
  }
}
