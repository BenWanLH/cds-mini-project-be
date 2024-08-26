package com.gds.mini.project.models.db;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer userId;
  @Column(unique = true)
  private String username;

  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "user_role_junction",
    joinColumns = {@JoinColumn(name = "userId")},
    inverseJoinColumns = {@JoinColumn(name = "roleId")}
  )
  private Set<Role> authorities;

  public User() {
    super();
    this.authorities = new HashSet<Role>();
  }

  public User(String username, String password, Set<Role> authorities) {
    super();
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  public User(Integer userId, String username, String password, Set<Role> authorities) {
    super();
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  public void setAuthorities(Set<Role> authorities) {
    this.authorities = authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
