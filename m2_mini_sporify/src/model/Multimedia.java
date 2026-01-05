package model;

public abstract class Multimedia {
  private String name;
  private int durationSeconds;

  public Multimedia(String name, int durationSeconds) {
    this.name = name;
    this.durationSeconds = durationSeconds;
  }

  public Multimedia() {
    this.name = "Desconocido";
    this.durationSeconds = 0;
  }

  public Multimedia(String name) {
    this.name = name;
    this.durationSeconds = 0;
  }

  // getters and setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getDurationSeconds() {
    return durationSeconds;
  }

  public void setDurationSeconds(int durationSeconds) {
    this.durationSeconds = durationSeconds;
  }

  public abstract String getInfo();
}
