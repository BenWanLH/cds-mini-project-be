package com.gds.mini.project.models.enums;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Objects;

public enum Error {
  ROOM_CLOSED("1000", "Room closed", HttpStatus.FORBIDDEN),
  ROOM_NOT_JOINED("1001", "Room not joined", HttpStatus.FORBIDDEN),
  ROOM_NOT_OWNED("1003", "Room not owned", HttpStatus.FORBIDDEN),
  ROOM_NOT_FOUND("1002", "Room not found", HttpStatus.NOT_FOUND),
  USER_EXIST("2000", "User Exist", HttpStatus.BAD_REQUEST),
  BAD_CREDENTIALS("2001", "Bad Credentials", HttpStatus.BAD_REQUEST);

  public final String errorCode;

  public final String errorMessage;

  public final HttpStatus httpStatus;

  Error(String errorCode, String errorMessage, HttpStatus httpStatus) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.httpStatus = httpStatus;
  }

  public static Error getErrorByErrorCode(String errorCode) {
    for (Error error : Error.values()) {
      if (Objects.equals(error.errorCode, errorCode)) {
        return error;
      }
    }
    return null;
  }

}
