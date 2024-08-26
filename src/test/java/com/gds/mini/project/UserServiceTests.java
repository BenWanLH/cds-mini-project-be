package com.gds.mini.project;

import com.gds.mini.project.models.db.Role;
import com.gds.mini.project.models.db.User;
import com.gds.mini.project.repositories.UserRepository;
import com.gds.mini.project.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

  @MockitoBean
  private UserRepository mockUserRepository;

  @Autowired
  private UserService userService;

  @Test
  void loadUserByUserName_should_return_user() {
    Role fakeRole = new Role("USER");
    String testUsername = "testUser";
    User testUser = new User(1, testUsername, "password", Set.of(fakeRole));

    when(mockUserRepository.findByUsername(testUsername)).thenReturn(Optional.of(testUser));

    User user = userService.loadUserByUsername(testUsername);

    assertEquals(user, testUser);
  }

  @Test
  void loadUserByUserName_should_throw_error_when_no_user() {
    String testUsername = "testUser";

    when(mockUserRepository.findByUsername(testUsername)).thenReturn(Optional.empty());

    assertThrows(
      UsernameNotFoundException.class,
      () -> userService.loadUserByUsername(testUsername)
    );

  }

}
