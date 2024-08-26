package com.gds.mini.project;

import com.gds.mini.project.models.db.Room;
import com.gds.mini.project.models.db.Suggestion;
import com.gds.mini.project.repositories.SuggestionRepository;
import com.gds.mini.project.services.RoomService;
import com.gds.mini.project.services.SuggestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.when;

@SpringBootTest
public class SuggestionServiceTests {

  @MockitoBean
  private SuggestionRepository mockSuggestionRepository;

  @MockitoBean
  private RoomService mockRoomService;

  @Autowired
  private SuggestionService suggestionService;

  @Test
  void createSuggestion_suggestionRepository_should_have_called_save() {
    Integer testRoomId = 1;
    String suggestion = "testSuggestion";
    Room testRoom = new Room();
    Suggestion testSuggestion = new Suggestion(suggestion, testRoom);

    when(mockRoomService.findByRoomId(testRoomId)).thenReturn(testRoom);
    ArgumentCaptor<Suggestion> argument = ArgumentCaptor.forClass(Suggestion.class);

    suggestionService.createSuggestion(testRoomId, suggestion);

    Mockito.verify(mockSuggestionRepository).save(argument.capture());

    Assertions.assertEquals(testSuggestion.getSuggestion(), argument.getValue().getSuggestion());
    Assertions.assertEquals(testSuggestion.getRoom(), argument.getValue().getRoom());
  }

}
