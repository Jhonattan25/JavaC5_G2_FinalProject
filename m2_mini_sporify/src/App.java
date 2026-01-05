import java.util.Scanner;

import model.MusicLibrary;
import model.Playlist;
import model.Song;
import model.SporifyAccount;

public class App {

  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws Exception {

    MusicLibrary musicLibrary = createMusicLibrary();
    SporifyAccount sporifyAccount = createSporifyAccount(musicLibrary);

    int option = 0;
    do {
      showMenu();

      System.out.print("Ingrese su opción: ");
      option = scanner.nextInt();
      scanner.nextLine();
      System.out.println();

      switch (option) {
        case 1:
          login(sporifyAccount);
          break;
        case 2:
          musicLibraryManagement(sporifyAccount);
          break;
        case 3:
          playlistsManagement(sporifyAccount);
          break;
        case 4:
          songsManagement(sporifyAccount);
          break;
        case 5:
          if (validateSession(sporifyAccount)) {
            logOut(sporifyAccount);
          }
          break;
        case 6:
          System.out.println("Saliendo del sistema.");
          break;
        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          break;
      }

    } while (option != 6);

    scanner.close();
  }

  public static Song createSong(String name, String artist, int durationSeconds) {
    return new Song(name, artist, durationSeconds);
  }

  public static MusicLibrary createMusicLibrary() {
    Song song1 = createSong("Song A", "Artist A", 240);
    Song song2 = createSong("Song B", "Artist B", 300);
    Song song3 = createSong("Song C", "Artist C", 180);

    MusicLibrary musicLibrary = new MusicLibrary(2000);
    musicLibrary.addSong(song1);
    musicLibrary.addSong(song2);
    musicLibrary.addSong(song3);

    return musicLibrary;
  }

  public static SporifyAccount createSporifyAccount(MusicLibrary musicLibrary) {
    SporifyAccount sporifyAccount = new SporifyAccount("user1", "user1", 100, musicLibrary);
    return sporifyAccount;
  }

  public static void showMenu() {
    System.out.println("""

        === MINI-SPOTIFY ===

        1. Iniciar sesión
        2. Gestionar Biblioteca Musical
        3. Gestionar Playlists
        4. Reproducir canciones
        5. Cerrar sesión
        6. Salir del programa

        """);
  }

  public static void login(SporifyAccount sporifyAccount) {
    String user = requestUser();
    String password = requestPassword();

    sporifyAccount.login(user, password);
  }

  public static boolean validateSession(SporifyAccount sporifyAccount) {
    if (sporifyAccount.isSessionActive()) {
      return true;
    } else {
      System.out.println("Debe iniciar sesión para realizar esta acción.");
      return false;
    }
  }

  public static void musicLibraryManagement(SporifyAccount sporifyAccount) {
    int option = 0;
    do {
      showSubMenuMusicLibrary();

      System.out.print("Ingrese su opción: ");
      option = scanner.nextInt();
      scanner.nextLine();
      System.out.println();

      switch (option) {
        case 1:
          listSongsMusicLibrary(sporifyAccount.getMusicLibrary());
          break;
        case 2:
          if (validateSession(sporifyAccount)) {
            addSongToMusicLibrary(sporifyAccount.getMusicLibrary());
          }
          break;
        case 3:
          searchSongMusicLibraryByName(sporifyAccount.getMusicLibrary());
          break;
        case 4:
          break;
        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          break;
      }

    } while (option != 4);
  }

  public static void showSubMenuMusicLibrary() {
    System.out.println("""

        === Gestionar Biblioteca Musical ===

        1. Listar canciones
        2. Agregar canción
        3. Buscar por nombre
        4. Volver

        """);
  }

  public static void listSongsMusicLibrary(MusicLibrary musicLibrary) {
    System.out.println("=== Catalogo de canciones ===");
    System.out.println();
    musicLibrary.listCatalog();
  }

  public static void addSongToMusicLibrary(MusicLibrary musicLibrary) {

    String name = requestSongName();
    String artist = requestArtist();
    int durationSeconds = requestDurationSeconds();

    Song newSong = createSong(name, artist, durationSeconds);
    musicLibrary.addSong(newSong);
    System.out.println("Canción agregada exitosamente.");
  }

  public static void searchSongMusicLibraryByName(MusicLibrary musicLibrary) {
    
    String name = requestSongName();

    Song song = musicLibrary.searchSongByName(name);

    if (song != null) {
      System.out.println(song.getInfo());
    }
  }

  public static void playlistsManagement(SporifyAccount sporifyAccount) {
    int option = 0;
    do {
      showSubMenuPlaylists();

      System.out.print("Ingrese su opción: ");
      option = scanner.nextInt();
      scanner.nextLine();
      System.out.println();

      switch (option) {
        case 1:
          if (validateSession(sporifyAccount)) {
            createPlaylist(sporifyAccount);
          }
          break;
        case 2:
          listPlaylists(sporifyAccount);
          break;
        case 3:
          if (validateSession(sporifyAccount)) {
            addSongToPlaylist(sporifyAccount);
          }
          break;
        case 4:
          showSongsByPlaylist(sporifyAccount);
        case 5:
          break;
        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          break;
      }

    } while (option != 5);
  }

  public static void showSubMenuPlaylists() {
    System.out.println("""

        === Gestionar Listas De Reproducción ===

        1. Crear playlist
        2. Listar playlists
        3. Agregar canción a playlist
        4. Ver canciones
        5. Volver

        """);
  }

  public static void createPlaylist(SporifyAccount sporifyAccount) {
    String name = requestPlaylistName();

    Playlist newPlaylist = new Playlist(name, 200);
    sporifyAccount.addPlaylist(newPlaylist);
  }

  public static void listPlaylists(SporifyAccount sporifyAccount) {
    System.out.println("=== Lista de playlists ===");
    System.out.println();
    sporifyAccount.listPlaylists();
  }

  public static void addSongToPlaylist(SporifyAccount sporifyAccount) {
    String songName = requestSongName();
    Song song = sporifyAccount.getMusicLibrary().searchSongByName(songName);

    if (song != null) {
      String playlistName = requestPlaylistName();
      sporifyAccount.addSongToPlaylist(playlistName, song);
    }
  }

  public static void showSongsByPlaylist(SporifyAccount sporifyAccount) {
    String playlistName = requestPlaylistName();
    Playlist playlist = sporifyAccount.searchPlaylistByName(playlistName);

    if (playlist != null) {
      System.out.println("=== Canciones de las playlist " + playlistName + " ===");
      System.out.println();
      playlist.listSongs();
    }
  }

  public static void songsManagement(SporifyAccount sporifyAccount) {
    int option = 0;
    do {
      showSubMenuSongs();

      System.out.print("Ingrese su opción: ");
      option = scanner.nextInt();
      scanner.nextLine();
      System.out.println();

      switch (option) {
        case 1:
          if (validateSession(sporifyAccount)) {
            playSongByPlaylist(sporifyAccount);
          }
          break;
        case 2:
          if (validateSession(sporifyAccount)) {
            stopSongByPlaylist(sporifyAccount);
          }
          break;
        case 3:
          break;
        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          break;
      }

    } while (option != 3);
  }

  public static void showSubMenuSongs() {
    System.out.println("""

        === Reproducir canciones ===

        1. Reproducir canción desde playlist
        2. Detener reproducción desde playlist
        3. Volver

        """);
  }

  public static void playSongByPlaylist(SporifyAccount sporifyAccount) {
    String playlistName = requestPlaylistName();
    int songIndex = requestSongIndex();

    sporifyAccount.playSong(playlistName, songIndex - 1);
  }

  public static void stopSongByPlaylist(SporifyAccount sporifyAccount) {
    String playlistName = requestPlaylistName();
    int songIndex = requestSongIndex();

    sporifyAccount.stopSong(playlistName, songIndex - 1);
  }

  public static void logOut(SporifyAccount sporifyAccount) {
    sporifyAccount.logOut();
  }

  // === Request Validations ===
  public static String requestUser() {
    String user;
    boolean validUser = false;

    do {
      System.out.print("Ingrese su usuario: ");
      user = scanner.nextLine();
      validUser = isValidString(user);

      if (!validUser) {
        System.out.println("Usuario invalido. No debe estar vacio o contener solo espacios");
      }
    } while (!validUser);

    return user;
  }

  public static String requestPassword() {
    String password;
    boolean validPassword = false;

    do {
      System.out.print("Ingrese su contraseña: ");
      password = scanner.nextLine();
      validPassword = isValidString(password);

      if (!validPassword) {
        System.out.println("Contraseña invalida. No debe estar vacio o contener solo espacios");
      }
    } while (!validPassword);

    return password;
  }

  public static String requestSongName() {
    String name;
    boolean validName = false;

    do {
      System.out.print("Ingrese el nombre de la canción: ");
      name = scanner.nextLine();
      validName = isValidString(name);

      if (!validName) {
        System.out.println("Nombre invalido. No debe estar vacio o contener solo espacios");
      }
    } while (!validName);

    return name;
  }

  public static String requestArtist() {
    String artist;
    boolean validArtist = false;

    do {
      System.out.print("Ingrese el artista: ");
      artist = scanner.nextLine();
      validArtist = isValidString(artist);

      if (!validArtist) {
        System.out.println("Artista invalido. No debe estar vacio o contener solo espacios");
      }
    } while (!validArtist);

    return artist;
  }

  public static int requestDurationSeconds() {
    int durationSeconds;
    boolean validDurationSeconds = false;

    do {
      System.out.print("Ingrese la duración en segundos: ");
      durationSeconds = scanner.nextInt();

      validDurationSeconds = isValidInt(durationSeconds);

      if (!validDurationSeconds) {
        System.out.println("Duración invalida. Debe ser mayor a 0");
      }
    } while (!validDurationSeconds);
    scanner.nextLine();

    return durationSeconds;
  }

  public static int requestSongIndex() {
    int index;
    boolean validIndex = false;

    do {
      System.out.print("Ingrese el indice de la canción: ");
      index = scanner.nextInt();

      validIndex = isValidInt(index);

      if (!validIndex) {
        System.out.println("Duración invalida. Debe ser mayor a 0");
      }
    } while (!validIndex);
    scanner.nextLine();

    return index;
  }

  public static String requestPlaylistName() {
    String name;
    boolean validName = false;

    do {
      System.out.print("Ingrese el nombre de la playlist: ");
      name = scanner.nextLine();
      validName = isValidString(name);

      if (!validName) {
        System.out.println("Nombre invalido. No debe estar vacio o contener solo espacios");
      }
    } while (!validName);

    return name;
  }

  public static boolean isValidString(String input) {
    return !input.isBlank();
  }

  static boolean isValidInt(int input) {
    return input >= 0;
  }

}
