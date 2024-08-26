package com.gds.mini.project.models.dto.response;

import com.gds.mini.project.models.db.Suggestion;
import java.util.Set;

public class CreateRoomResponse {
  public Integer roomId;

  public Set<Suggestion> suggestions;

  public String status;

  public String selectedSuggestion;

  public boolean isOwner;

  public CreateRoomResponse(Integer roomId, Set<Suggestion> suggestions, String status, String selectedSuggestion, boolean isOwner) {
    this.roomId = roomId;
    this.suggestions = suggestions;
    this.status = status;
    this.selectedSuggestion = selectedSuggestion;
    this.isOwner = isOwner;
  }
}
