package com.gds.mini.project.models.db;

import com.gds.mini.project.models.enums.RoomStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer roomId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "userId")
  private User owner;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Suggestion> suggestions;

  private String status;

  private String selectedSuggestion;

  public Room() {
  }

  public Room(Integer roomId, User owner, List<Suggestion> suggestions, String status, String selectedSuggestion) {
    this.roomId = roomId;
    this.owner = owner;
    this.suggestions = suggestions;
    this.status = status;
    this.selectedSuggestion = selectedSuggestion;
  }

  public Room(User owner, List<Suggestion> suggestions) {
    this.owner = owner;
    this.suggestions = suggestions;
    this.status = RoomStatus.OPEN.status;
  }

  public Room(User owner, List<Suggestion> suggestions, String status, String selectedSuggestion) {
    this.owner = owner;
    this.suggestions = suggestions;
    this.status = status;
    this.selectedSuggestion = selectedSuggestion;
  }

  public Integer getRoomId() {
    return roomId;
  }

  public String getSelectedSuggestion() {
    return selectedSuggestion;
  }

  public void setSelectedSuggestion(String selectedSuggestion) {
    this.selectedSuggestion = selectedSuggestion;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public List<Suggestion> getSuggestions() {
    return suggestions;
  }

  public void setSuggestions(List<Suggestion> suggestions) {
    this.suggestions = suggestions;
  }
}
