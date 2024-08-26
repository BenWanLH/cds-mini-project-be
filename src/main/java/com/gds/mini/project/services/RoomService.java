package com.gds.mini.project.services;

import com.gds.mini.project.models.db.Room;
import com.gds.mini.project.models.db.Suggestion;
import com.gds.mini.project.models.db.User;
import com.gds.mini.project.models.dto.response.BasicRoomData;
import com.gds.mini.project.models.dto.response.CloseRoomResponse;
import com.gds.mini.project.models.dto.response.GetAllRoomResponse;
import com.gds.mini.project.models.dto.response.GetRoomResponse;
import com.gds.mini.project.models.enums.Error;
import com.gds.mini.project.models.enums.RoomStatus;
import com.gds.mini.project.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class RoomService {
  @Autowired
  private RoomRepository roomRepository;

  public Room findByRoomId(Integer roomId) throws IllegalStateException {
    return roomRepository.findByRoomId(roomId).orElseThrow(() -> new IllegalStateException(Error.ROOM_NOT_FOUND.errorCode));
  }
  public Set<Room> findByOwner(User user) throws IllegalStateException {
    return roomRepository.findByOwner(user).orElseThrow(() -> new IllegalStateException(Error.ROOM_NOT_FOUND.errorCode));
  }

  public Room createRoom(User user) {

    List<Suggestion> suggestions = List.of();

    return roomRepository.save(new Room(user, suggestions));
  }

  public GetAllRoomResponse getAllRoomByUser(User user) {

    Set<Room> rooms = findByOwner(user);

    List<BasicRoomData> basicRoomDatas = rooms.stream().map(room ->
      new BasicRoomData(room.getRoomId(), room.getStatus())).toList();

    return new GetAllRoomResponse(basicRoomDatas);
  }

  public CloseRoomResponse closeRoom(Integer roomId, User user) {
    Room room = findByRoomId(roomId);

    if(!room.getOwner().equals(user)) throw new IllegalStateException(Error.ROOM_NOT_OWNED.errorCode);

    if(!room.getSuggestions().isEmpty()) {
      List<Suggestion> suggestions = room.getSuggestions();
      Random random = new Random();
      int randomNumber = random.nextInt(suggestions.size());
      room.setSelectedSuggestion(suggestions.get(randomNumber).getSuggestion());
    }

    room.setStatus(RoomStatus.CLOSE.status);
    roomRepository.save(room);

    return new CloseRoomResponse(room.getSelectedSuggestion());
  }

  public GetRoomResponse getRoom(Integer roomId, User user) {
    Room room = findByRoomId(roomId);

    boolean isOwner = false;

    if(user != null) {
      isOwner = room.getOwner().equals(user);
    }

    return new GetRoomResponse(
      room.getRoomId(),
      room.getSuggestions(),
      room.getStatus(),
      room.getSelectedSuggestion(),
      isOwner
    );

  }
}
