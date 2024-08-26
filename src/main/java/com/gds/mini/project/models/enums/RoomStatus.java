package com.gds.mini.project.models.enums;

public enum RoomStatus {
  OPEN("OPEN"),
  CLOSE("CLOSE");

  public final String status;

  RoomStatus(String status) {
    this.status = status;
  }
}
