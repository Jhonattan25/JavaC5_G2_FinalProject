package model;

public class Song extends Multimedia implements Reproducible {

  private String artist;

  public Song(String title, int durationSeconds, String artist) {
    super(title, durationSeconds);
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
        Cancion: %s,
        Duracion: %s,
        Artista: %s
        """, getTitle(), getDurationSeconds(), getArtist());
  }

  @Override
  public void play() {
    System.out.println("Produciendo la canción: " + getTitle() + " del artista: " + getArtist());
  }

  @Override
  public void stop() {
    System.out.println("Deteniendo la canción: " + getTitle());
  }

}
