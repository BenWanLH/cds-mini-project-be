package com.gds.mini.project.controllers;

import com.gds.mini.project.models.dto.response.LoginResponse;
import com.gds.mini.project.models.dto.request.RegisterUserRequest;
import com.gds.mini.project.models.db.User;
import com.gds.mini.project.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/login")
  public LoginResponse login(@RequestBody RegisterUserRequest request) {
    return authenticationService.loginUser(request.username, request.password);
  }

  @PostMapping("/register")
  public User registerUser(@RequestBody RegisterUserRequest request) {
    return authenticationService.registerUser(request.username, request.password);
  }
}
