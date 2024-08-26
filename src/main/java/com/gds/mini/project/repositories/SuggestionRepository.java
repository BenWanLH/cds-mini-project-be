package com.gds.mini.project.repositories;

import com.gds.mini.project.models.db.Room;
import com.gds.mini.project.models.db.Suggestion;
import com.gds.mini.project.models.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
}
