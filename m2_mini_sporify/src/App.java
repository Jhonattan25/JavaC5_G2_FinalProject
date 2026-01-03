import java.util.Scanner;

import model.MusicLibrary;
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
          // showTotalValue();
          break;
        case 4:
          // showProductSummary();
          break;
        case 5:
          // clearProductData();
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

  public static Song createSong(String title, String artist, int durationSeconds) {
    return new Song(title, artist, durationSeconds);
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
    System.out.print("Ingrese su usuario: ");
    String user = scanner.nextLine();

    System.out.print("Ingrese su contraseña: ");
    String password = scanner.nextLine();
    System.out.println();

    sporifyAccount.login(user, password);
    System.out.println();
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
    musicLibrary.listCatalog();
  }

  public static void addSongToMusicLibrary(MusicLibrary musicLibrary) {

    System.out.print("Ingrese el titulo: ");
    String title = scanner.nextLine();

    System.out.print("Ingrese el artista: ");
    String artist = scanner.nextLine();

    System.out.print("Ingrese la duración en segundos: ");
    int durationSeconds = scanner.nextInt();
    scanner.nextLine();

    Song newSong = createSong(title, artist, durationSeconds);

    musicLibrary.addSong(newSong);
    System.out.println("Canción agregada exitosamente.");
  }

  public static boolean validateSession(SporifyAccount sporifyAccount) {
    if (sporifyAccount.isSessionActive()) {
      return true;
    } else {
      System.out.println("Debe iniciar sesión para realizar esta acción.");
      return false;
    }
  }

  public static void searchSongMusicLibraryByName(MusicLibrary musicLibrary) {
    System.out.print("Ingrese el nombre de la canción a buscar: ");
    String name = scanner.nextLine();

    Song foundSong = musicLibrary.searchSong(name);

    if (foundSong != null) {
      System.out.println(foundSong.getInfo());
    } else {
      System.out.println("Canción no encontrada en la biblioteca musical.");
    }
  }
}
