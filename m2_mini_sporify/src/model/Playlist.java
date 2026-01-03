package model;

public class Playlist {
  private String name;
  private Song[] songs;
  private int songsCounter;

  public Playlist(String name, int maxSongs) {
    this.name = name;
    this.songs = new Song[maxSongs];
    this.songsCounter = 0;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  public void addSong(Song song) {
    if (songsCounter < songs.length) {
      songs[songsCounter] = song;
      songsCounter++;
    } else {
      System.out.println("No se pueden agregar más canciones. La Paylist está llena.");
    }
  }

  public void listSongs() {
    for (int i = 0; i < songsCounter; i++) {
      System.out.println((i + 1) + ". " + songs[i].getTitle() + " - " + songs[i].getArtist());
    }
  }

  public void playSong(int index) {
    if (index >= 0 && index < songsCounter) {
      songs[index].play();
    } else {
      System.out.println("Índice de canción inválido.");
    }
  }

  public boolean isFull() {
    return songsCounter >= songs.length;
  }
}
