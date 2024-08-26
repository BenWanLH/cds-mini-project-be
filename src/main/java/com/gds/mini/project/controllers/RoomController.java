package com.gds.mini.project.controllers;

import com.gds.mini.project.models.db.Room;
import com.gds.mini.project.models.db.Suggestion;
import com.gds.mini.project.models.db.User;
import com.gds.mini.project.models.dto.request.AddSuggestionRequest;
import com.gds.mini.project.models.dto.response.BasicRoomData;
import com.gds.mini.project.models.dto.response.CloseRoomResponse;
import com.gds.mini.project.models.dto.response.GetAllRoomResponse;
import com.gds.mini.project.models.dto.response.GetRoomResponse;
import com.gds.mini.project.services.RoomService;
import com.gds.mini.project.services.SuggestionService;
import com.gds.mini.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "*")
public class RoomController {

  @Autowired
  private UserService userService;

  @Autowired
  private RoomService roomService;

  @Autowired
  private SuggestionService suggestionService;

  @PostMapping("/create")
  public BasicRoomData createRoom(Principal principal) {
    String username = principal.getName();

    User user = userService.loadUserByUsername(username);

    Room room = roomService.createRoom(user);

    return new BasicRoomData(
      room.getRoomId(),
      room.getStatus()
    );
  }

  @PostMapping("{roomId}/close")
  public CloseRoomResponse closeRoom(@PathVariable("roomId") String roomId, Principal principal) {
    String username = principal.getName();

    User user = userService.loadUserByUsername(username);

    return roomService.closeRoom(Integer.parseInt(roomId), user);
  }

  @GetMapping("")
  public GetRoomResponse getRoom(@RequestParam("roomId") String roomId, Principal principal) {
    User user = null;

    if(principal != null) {
      String username = principal.getName();
      user = (User) userService.loadUserByUsername(username) ;
    }

    return roomService.getRoom(Integer.parseInt(roomId), user);
  }

  @GetMapping("/all")
  public GetAllRoomResponse getAllRoomsByUser(Principal principal) {
    String username = principal.getName();

    User user = userService.loadUserByUsername(username);

    return roomService.getAllRoomByUser(user);
  }

  @PutMapping("/{roomId}/suggestion")
  public Suggestion addSuggestion(@PathVariable("roomId") String roomId, @RequestBody AddSuggestionRequest request) {
    return this.suggestionService.createSuggestion(Integer.parseInt(roomId), request.suggestion);
  }
}
