package com.gds.mini.project.models.dto.response;

import java.util.List;

public class GetAllRoomResponse {

  public List<BasicRoomData> rooms;

  public GetAllRoomResponse(List<BasicRoomData> rooms) {
    this.rooms = rooms;
  }
}
