package com.gds.mini.project.services;

import com.gds.mini.project.models.db.Role;
import com.gds.mini.project.models.db.User;
import com.gds.mini.project.models.dto.response.LoginResponse;
import com.gds.mini.project.models.enums.Error;
import com.gds.mini.project.repositories.RoleRepository;
import com.gds.mini.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenService jwtTokenService;

  public User registerUser(String username, String password) {
    String encodedPassword = passwordEncoder.encode(password);
    Role userRole = roleRepository.findByAuthority("USER").get();

    Set<Role> authorities = new HashSet<>();
    authorities.add(userRole);

    return userRepository.save(new User(username, encodedPassword, authorities));
  }

  public LoginResponse loginUser(String username, String password) {
    try {
      Authentication auth = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(username, password));

      String token = jwtTokenService.generateJwt(auth);

      return new LoginResponse(token);
    } catch (AuthenticationException e) {
      System.out.println(e.getMessage());
      throw new RuntimeException(Error.BAD_CREDENTIALS.errorCode);
    }
  }

}
