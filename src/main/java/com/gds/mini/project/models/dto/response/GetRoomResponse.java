package com.gds.mini.project.models.dto.response;

import com.gds.mini.project.models.db.Suggestion;

import java.util.List;

public class GetRoomResponse {
  public Integer roomId;

  public List<Suggestion> suggestions;

  public String status;

  public String selectedSuggestion;

  public boolean isOwner;

  public GetRoomResponse(Integer roomId, List<Suggestion> suggestions, String status, String selectedSuggestion, boolean isOwner) {
    this.roomId = roomId;
    this.suggestions = suggestions;
    this.status = status;
    this.selectedSuggestion = selectedSuggestion;
    this.isOwner = isOwner;
  }
}
