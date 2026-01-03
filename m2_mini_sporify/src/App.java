import model.MusicLibrary;
import model.Playlist;
import model.Song;
import model.SporifyAccount;

public class App {

  MusicLibrary musicLibrary = new MusicLibrary(2000);
    public static void main(String[] args) throws Exception {

      SporifyAccount sporifyAccount1 = createAccount("user1", "password1", 100, 100);


      Song song1 = new Song("Song A", 3, "Artist A");
      Song song2 = new Song("Song B", 4, "Artist B");

      Playlist playlist1 = new Playlist("My Favorites Songs", 20);
      playlist1.addSong(song1);
      playlist1.addSong(song2);
    }

    public static void showMenu() {

    }

    public static SporifyAccount createAccount(String user, String password, int maxPlaylists, int maxSongs) {
      SporifyAccount sporifyAccount = new SporifyAccount(user, password, maxPlaylists, maxSongs);
      return sporifyAccount;
    }
}
