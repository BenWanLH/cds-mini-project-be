package com.gds.mini.project.models.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "suggestion")
public class Suggestion {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer suggestionId;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "roomId")
  @JsonIgnore
  private Room room;

  private String suggestion;

  public Suggestion() {}

  public Suggestion(Integer suggestionId, Room room, String suggestion) {
    this.suggestionId = suggestionId;
    this.room = room;
    this.suggestion = suggestion;
  }

  public Suggestion(String suggestion, Room room) {
    this.suggestion = suggestion;
    this.room = room;
  }

  public String getSuggestion() {
    return suggestion;
  }

  public void setSuggestion(String suggestion) {
    this.suggestion = suggestion;
  }

  public Integer getSuggestionId() {
    return suggestionId;
  }

  public void setSuggestionId(Integer suggestionId) {
    this.suggestionId = suggestionId;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }
}
