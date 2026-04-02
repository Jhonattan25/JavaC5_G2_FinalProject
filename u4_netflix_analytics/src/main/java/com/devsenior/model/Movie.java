package com.devsenior.model;

public class Movie {
  private String id;
  private String title;
  private Genre genre;
  private int duration;

  public Movie(String id, String title, Genre genre, int duration) {
    this.id = id;
    this.title = title;
    this.genre = genre;
    this.duration = duration;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Genre getGenre() {
    return genre;
  }
  public void setGenre(Genre genre) {
    this.genre = genre;
  }
  public int getDuration() {
    return duration;
  }
  public void setDuration(int duration) {
    this.duration = duration;
  }
  
  @Override
  public String toString() {
    return "Movie [title=" + title + ", genre=" + genre + "]";
  }
}
