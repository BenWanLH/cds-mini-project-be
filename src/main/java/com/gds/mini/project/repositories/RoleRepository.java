package com.gds.mini.project.repositories;

import com.gds.mini.project.models.db.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByAuthority(String authority);
}
