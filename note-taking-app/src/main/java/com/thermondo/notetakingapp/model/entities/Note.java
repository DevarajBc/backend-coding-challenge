package com.thermondo.notetakingapp.model.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Note implements Serializable {

  @ElementCollection
  private List<String> tags = new ArrayList<>();

  @Id // this annotation defines the primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // this annotation is used to define the primary key generation strategy
  private Long id;

  @NotBlank // this annotation is used to validate that the field is not null
  private String title;

  @NotBlank
  private String body;

  @NotBlank
  private Long userId;


  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
