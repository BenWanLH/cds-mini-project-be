package com.gds.mini.project.repositories;

import com.gds.mini.project.models.db.Room;
import com.gds.mini.project.models.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
  Optional<Set<Room>> findByOwner(User owner);

  Optional<Room> findByRoomId(Integer roomId);


//  Set<Room> findByParticipant(User participant);
}
