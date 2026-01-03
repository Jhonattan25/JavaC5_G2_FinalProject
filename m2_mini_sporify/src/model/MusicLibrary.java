package model;

public class MusicLibrary {
  private Song[] songs;
  private int songsCounter;

  public MusicLibrary(int maxSongs) {
    this.songs = new Song[maxSongs];
    this.songsCounter = 0;
  }

  public void addSong(Song song) {
    if (songsCounter < songs.length) {
      songs[songsCounter] = song;
      songsCounter++;
    } else {
      System.out.println("No se puede agregar más canciones, capacidad máxima alcanzada.");
    }
  }

  public Song searchSong(String name) {
    for (int i = 0; i < songsCounter; i++) {
      if (name.equalsIgnoreCase(songs[i].getTitle())) {
        return songs[i];
      }
    }
    return null;
  }

  public void listCatalog() {
    System.out.println("Catalogo");

    for (int i = 0; i < songs.length; i++) {
      System.out.println(i + ". " + songs[i].getTitle() + " - " + songs[i].getArtist());
    }
  }
}
