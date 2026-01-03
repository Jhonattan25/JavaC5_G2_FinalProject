package model;

public class SporifyAccount {
  private String user;
  private String password;
  private Playlist[] playlists;
  private int playlistsCounter;
  private MusicLibrary musicLibrary;
  private boolean isSessionActive;

  public SporifyAccount(String user, String password, int maxPlaylists, MusicLibrary musicLibrary) {
    this.user = user;
    this.password = password;
    this.playlists = new Playlist[maxPlaylists];
    this.playlistsCounter = 0;
    this.musicLibrary = musicLibrary;
    this.isSessionActive = false;
  }

  public MusicLibrary getMusicLibrary() {
    return musicLibrary;
  }

  public void setMusicLibrary(MusicLibrary musicLibrary) {
    this.musicLibrary = musicLibrary;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isSessionActive() {
    return isSessionActive;
  }

  public void setSessionActive(boolean isSessionActive) {
    this.isSessionActive = isSessionActive;
  }

  public void login(String user, String password) {
    if (user.equalsIgnoreCase(getUser()) && password.equalsIgnoreCase(getPassword())) {
      setSessionActive(true);
      System.out.println("Sesion iniciada correctamente.");
    } else {
      System.out.println("Credenciales incorrectas.");
    }
  }

  public void addPlaylist(Playlist newPlaylist) {
    if (isSessionActive) {
      if (playlistsCounter < playlists.length) {
        playlists[playlistsCounter] = newPlaylist;
        playlistsCounter++;
        System.out.println("Playlist agregada correctamente.");
      } else {
        System.out.println("No se puede agregar más playlist, capacidad máxima alcanzada.");
      }
    } else {
      System.out.println("Debe iniciar sesión para agregar una playlist.");
    }
  }

  public void listPlaylists() {
    for (int i = 0; i < playlistsCounter; i++) {
      System.out.println((i + 1) + ". " + playlists[i].getName());
    }
  }

  public void addSongToPlaylist(String playlistName, Song newSong) {

    Playlist playlist = searchPlaylistByName(playlistName);

    if (playlist != null) {
      playlist.addSong(newSong);
      System.out.println("Canción agregada a la playlist correctamente.");
    }
  }

  public void playSong(String playlistName, int indexSong) {

    Playlist playlist = searchPlaylistByName(playlistName);

    if (playlist != null) {
      playlist.playSong(indexSong);
    }
  }

  public Playlist searchPlaylistByName(String playlistName) {
    for (int i = 0; i < playlistsCounter; i++) {
      if (playlistName.equalsIgnoreCase(playlists[i].getName())) {
        return playlists[i];
      }
    }
    System.out.println("Playlist no encontrada.");
    return null;
  }

  public void logOut() {
    setSessionActive(false);
    System.out.println("Sesión cerrada.");
  }

  public void addSongToMusicLibrary(Song song) {
    musicLibrary.addSong(song);
  }
}
