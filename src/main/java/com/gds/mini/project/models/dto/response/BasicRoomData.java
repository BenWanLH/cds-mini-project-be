package com.gds.mini.project.models.dto.response;

import java.util.Set;

public class BasicRoomData {
  public Integer roomId;

  public String status;

  public BasicRoomData(Integer roomId, String status) {
    this.roomId = roomId;
    this.status = status;
  }
}
