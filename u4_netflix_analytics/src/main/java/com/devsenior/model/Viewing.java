package com.devsenior.model;

public class Viewing {
  private User user;
  private Movie movie;
  private int watchedDurationMin;

  public Viewing(User user, Movie movie, int watchedDurationMin) {
    this.user = user;
    this.movie = movie;
    this.watchedDurationMin = watchedDurationMin;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public int getWatchedDurationMin() {
    return watchedDurationMin;
  }

  public void setWatchedDurationMin(int watchedDurationMin) {
    this.watchedDurationMin = watchedDurationMin;
  }

  @Override
  public String toString() {
    return "Viewing [user=" + user + ", movie=" + movie + ", watchedDurationMin=" + watchedDurationMin + "]";
  }
}
