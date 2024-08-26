package com.gds.mini.project.models.dto.response;

import com.gds.mini.project.models.db.User;

public class UserResponse {
  private Integer userId;

  private String username;

  public UserResponse(User user) {
    this.userId = user.getUserId();
    this.username = user.getUsername();
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
