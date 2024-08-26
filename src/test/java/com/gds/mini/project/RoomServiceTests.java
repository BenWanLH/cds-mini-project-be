package com.gds.mini.project;

import com.gds.mini.project.models.db.Room;
import com.gds.mini.project.models.db.Suggestion;
import com.gds.mini.project.models.db.User;
import com.gds.mini.project.models.dto.response.BasicRoomData;
import com.gds.mini.project.models.dto.response.CloseRoomResponse;
import com.gds.mini.project.models.dto.response.GetAllRoomResponse;
import com.gds.mini.project.models.dto.response.GetRoomResponse;
import com.gds.mini.project.models.enums.RoomStatus;
import com.gds.mini.project.repositories.RoomRepository;
import com.gds.mini.project.services.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class RoomServiceTests {

  @MockitoBean
  RoomRepository mockRoomRepository;

  private User testUser = new User();

  @Autowired
  RoomService roomService;

  @Test
  void findRoomById_should_return_room() {
    Integer roomId = 1;

    Room testRoom = new Room();
    when(mockRoomRepository.findByRoomId(roomId)).thenReturn(Optional.of(testRoom));

    Room room = roomService.findByRoomId(roomId);

    Assertions.assertEquals(room, testRoom);
  }

  @Test
  void findRoomById_should_throw_illegalStateException_if_room_not_found() {
    Integer roomId = 2;

    when(mockRoomRepository.findByRoomId(roomId)).thenReturn(Optional.empty());

    assertThrows(
      IllegalStateException.class,
      () -> roomService.findByRoomId(roomId)
    );
  }

  @Test
  void createRoom_should_call_roomRepository_save() {
    User mockUser = new User();

    ArgumentCaptor<Room> argument = ArgumentCaptor.forClass(Room.class);

    Room room = roomService.createRoom(mockUser);

    Mockito.verify(mockRoomRepository).save(argument.capture());

    Assertions.assertEquals(mockUser, argument.getValue().getOwner());
    Assertions.assertEquals(List.of(), argument.getValue().getSuggestions());
  }

  @Test
  void getAllRoomByUser_should_return_getAllRoomResponse() {
    User mockUser = new User();
    Integer roomId = 1;
    String status = RoomStatus.CLOSE.status;

    Room testRoom = new Room(roomId, mockUser, List.of(), status, null);
    when(mockRoomRepository.findByOwner(any())).thenReturn(Optional.of(Set.of(testRoom)));

    GetAllRoomResponse rooms = roomService.getAllRoomByUser(mockUser);

    Assertions.assertEquals(rooms.rooms.size(), 1);

    BasicRoomData room = rooms.rooms.get(0);

    Assertions.assertEquals(room.roomId, roomId);
    Assertions.assertEquals(room.status, status);
  }

  @Test
  void getRoom_should_return_getRoomResponse_with_isOwner_true() {
    User mockUser = new User();
    Integer roomId = 1;
    List<Suggestion> suggestions = List.of();
    String status = RoomStatus.CLOSE.status;

    Room testRoom = new Room(roomId, mockUser, suggestions, status, null);
    when(mockRoomRepository.findByRoomId(roomId)).thenReturn(Optional.of(testRoom));

    GetRoomResponse room = roomService.getRoom(roomId, mockUser);

    Assertions.assertTrue(room.isOwner);
    Assertions.assertEquals(room.roomId, roomId);
    Assertions.assertEquals(room.suggestions, suggestions);
  }

  @Test
  void getRoom_should_return_getRoomResponse_with_isOwner_false() {
    User mockUser = new User();
    User mockUser2 = new User();
    Integer roomId = 1;
    List<Suggestion> suggestions = List.of();
    String status = RoomStatus.CLOSE.status;

    Room testRoom = new Room(roomId, mockUser, suggestions, status, null);
    when(mockRoomRepository.findByRoomId(roomId)).thenReturn(Optional.of(testRoom));

    GetRoomResponse room = roomService.getRoom(roomId, mockUser2);

    Assertions.assertFalse(room.isOwner);
    Assertions.assertEquals(room.roomId, roomId);
    Assertions.assertEquals(room.suggestions, suggestions);
  }

  @Test
  void closeRoom_should_return_closeRoomResponse() {
    Integer roomId = 1;
    User mockUser = new User();
    List<Suggestion> suggestions = new ArrayList<>();

    List<String> suggestionStrings = new ArrayList<>();

    for(int i =0 ; i < 3; i++) {
      Suggestion suggestion = new Suggestion();
      String suggestionString = Integer.toString(i);
      suggestion.setSuggestion(suggestionString);
      suggestions.add(suggestion);
      suggestionStrings.add(suggestionString);
    }
    Room testRoom = new Room(roomId, mockUser, suggestions, RoomStatus.CLOSE.status, null);

    when(mockRoomRepository.findByRoomId(roomId)).thenReturn(Optional.of(testRoom));

    CloseRoomResponse closeRoomResponse = roomService.closeRoom(roomId, mockUser);

    assertTrue(suggestionStrings.contains(closeRoomResponse.selectedSuggestion));
  }

  @Test
  void closeRoom_should_return_selectedSuggestion_with_null() {
    Integer roomId = 1;
    User mockUser = new User();
    List<Suggestion> suggestions = List.of();

    Room testRoom = new Room(roomId, mockUser, suggestions, RoomStatus.CLOSE.status, null);

    when(mockRoomRepository.findByRoomId(roomId)).thenReturn(Optional.of(testRoom));

    CloseRoomResponse closeRoomResponse = roomService.closeRoom(roomId, mockUser);

    assertNull(closeRoomResponse.selectedSuggestion);
  }

  @Test
  void closeRoom_should_throw_illegalStateException_if_not_owner() {
    Integer roomId = 1;
    User mockUser = new User();
    User mockUser2 = new User();
    List<Suggestion> suggestions = List.of();

    Room testRoom = new Room(roomId, mockUser, suggestions, RoomStatus.CLOSE.status, null);

    when(mockRoomRepository.findByRoomId(roomId)).thenReturn(Optional.of(testRoom));

    assertThrows(
      IllegalStateException.class,
      () -> roomService.closeRoom(roomId, mockUser2)
    );
  }

}
