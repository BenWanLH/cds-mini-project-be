package com.gds.mini.project.services;

import com.gds.mini.project.models.db.Room;
import com.gds.mini.project.models.db.Suggestion;
import com.gds.mini.project.repositories.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SuggestionService {
  @Autowired
  private RoomService roomService;

  @Autowired
  private SuggestionRepository suggestionRepository;

  public Suggestion createSuggestion(Integer roomId, String suggestion) {

    Room room = roomService.findByRoomId(roomId);

    return this.suggestionRepository.save(new Suggestion(suggestion, room));

  }
}
