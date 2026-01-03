package model;

public abstract class Multimedia {
  private String title;
  private int durationSeconds;

  public Multimedia(String title, int durationSeconds) {
    this.title = title;
    this.durationSeconds = durationSeconds;
  }

  public Multimedia() {
    this.title = "Desconocido";
    this.durationSeconds = 0;
  }

  public Multimedia(String title) {
    this.title = title;
    this.durationSeconds = 0;
  }

  // getters and setters
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getDurationSeconds() {
    return durationSeconds;
  }

  public void setDurationSeconds(int durationSeconds) {
    this.durationSeconds = durationSeconds;
  }

  public abstract String getInfo();
}
