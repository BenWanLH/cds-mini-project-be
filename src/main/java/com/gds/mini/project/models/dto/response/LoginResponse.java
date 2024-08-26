package com.gds.mini.project.models.dto.response;

import com.gds.mini.project.models.db.User;

public class LoginResponse {
  private String jwt;

  public LoginResponse(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }
}
