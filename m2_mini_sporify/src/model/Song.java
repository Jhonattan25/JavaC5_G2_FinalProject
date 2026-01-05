package model;

public class Song extends Multimedia implements Reproducible {

  private String artist;

  public Song(String name, String artist, int durationSeconds) {
    super(name, durationSeconds);
    this.artist = artist;
  }

  public Song() {
    super();
    this.artist = "Desconocido";
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  @Override
  public String getInfo() {
    return String.format("""

        === Información de la canción ===

        Name: %s
        Artista: %s
        Duracion: %s
        """, getName(), getArtist(), getDurationSeconds());
  }

  @Override
  public void play() {
    System.out.println("Reproduciendo la canción: " + getName() + " del artista: " + getArtist());
  }

  @Override
  public void stop() {
    System.out.println("Deteniendo la canción: " + getName());
  }

}
