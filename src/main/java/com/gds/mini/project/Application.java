package com.gds.mini.project;

import com.gds.mini.project.models.db.Role;
import com.gds.mini.project.models.db.User;
import com.gds.mini.project.repositories.RoleRepository;
import com.gds.mini.project.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return  args -> {
      if(roleRepository.findByAuthority("USER").isPresent()) {
        return;
      }
      Role userRole = roleRepository.save(new Role("USER"));

      Set<Role> roles = new HashSet<>();
      roles.add(userRole);

      User user = new User( "admin", passwordEncoder.encode("password"), roles);
      userRepository.save(user);
    };
  }

}
