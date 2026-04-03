package com.devsenior.ui;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.devsenior.model.Genre;
import com.devsenior.model.Movie;
import com.devsenior.model.User;
import com.devsenior.service.AnalyticsService;

public class ConsoleUI {
  private AnalyticsService analyticsService;
  private Scanner scanner;

  public ConsoleUI(AnalyticsService analyticsService) {
    this.analyticsService = analyticsService;
    scanner = new Scanner(System.in);
  }

  public void start() {
    int option = -1;

    do {

      showMenu();

      System.out.print("Ingrese su opción: ");
      option = scanner.nextInt();
      scanner.nextLine();
      System.out.println();

      switch (option) {
        case 1:
          System.out.println("=== Peliculas mas vistas ===");
          analyticsService.getMostWatchedMovies()
              .forEach(ConsoleUI::showMostWatchedMovies);
          break;
        case 2:
          System.out.println("=== Peliculas por genero ===");
          analyticsService.getMoviesByGenre().entrySet()
              .forEach(ConsoleUI::showMoviesByGenre);
          break;
        case 3:
          System.out.println("=== Tiempo total visto por usuario ===");
          analyticsService.getwatchedDurationByUser().entrySet()
              .forEach(ConsoleUI::showWatchedDurationByUser);
          break;
        case 4:
          System.out.println("=== Top usuarios ===");
          analyticsService.getTop3Users().forEach(ConsoleUI::showWatchedDurationByUser);
          break;
        case 5:
          System.out.println("=== Promedio duracion por genero ===");
          analyticsService.getAverageMovieDurationByGenre().entrySet()
              .forEach(ConsoleUI::showAverageDurationByGenre);
          break;
        case 0:
          System.out.println("Saliendo del sistema.");
          break;
        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
          break;
      }

    } while (option != 0);

    scanner.close();
  }

  public static void showMenu() {
    System.out.println("""

        === NETFLIX ANALYTICS ===

        1. Peliculas mas vistas
        2. Peliculas por genero
        3. Tiempo total visto por usuario
        4. Top usuarios
        5. Promedio duracion por genero
        0. Salir

        """);
  }

  private static void showMostWatchedMovies(Map.Entry<Movie, Long> movie) {
    System.out.println(movie.getKey().getTitle() + " -> " + movie.getValue() + " visualizaciones");
  }

  private static void showMoviesByGenre(Map.Entry<Genre, List<Movie>> genre) {
    System.out.print(genre.getKey() + " -> ");
    genre.getValue().forEach(movie -> System.out.print(movie.getTitle() + ", "));
    System.out.println();
  }

  private static void showWatchedDurationByUser(Map.Entry<User, Integer> user) {
    System.out.println(user.getKey().getName() + " -> " + user.getValue() + " minutos");
  }

  private static void showAverageDurationByGenre(Map.Entry<Genre, Double> genre) {
    System.out.println(genre.getKey() + " -> " + genre.getValue() + " minutos");
  }
}
