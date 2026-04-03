package com.devsenior.service;

import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.devsenior.exception.UserNotFoundException;
import com.devsenior.model.Genre;
import com.devsenior.model.Movie;
import com.devsenior.model.User;
import com.devsenior.model.Viewing;
import com.devsenior.repository.DataRepository;

public class AnalyticsService {

  private DataRepository dataRepository;

  public AnalyticsService(DataRepository dataRepository) {
    this.dataRepository = dataRepository;
  }

  public List<Map.Entry<Movie, Long>> getMostWatchedMovies() {
    return dataRepository.viewings.stream()
        .collect(Collectors.groupingBy(Viewing::getMovie, Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.<Movie, Long>comparingByValue().reversed())
        .toList();
  }

  public Map<Genre, List<Movie>> getMoviesByGenre() {
    return dataRepository.movies.stream()
        .collect(Collectors.groupingBy(Movie::getGenre));
  }

  public Map<User, Integer> getWatchedDurationByUser() {
    return dataRepository.viewings.stream()
        .collect(Collectors.groupingBy(Viewing::getUser, Collectors.summingInt(Viewing::getWatchedDurationMin)));
  }

  public List<Map.Entry<User, Integer>> getTop3Users() {
    return getWatchedDurationByUser().entrySet().stream()
        .sorted(Map.Entry.<User, Integer>comparingByValue().reversed())
        .limit(3)
        .toList();
  }

  public Map<Genre, Double> getAverageMovieDurationByGenre() {
    return dataRepository.movies.stream()
        .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingInt(Movie::getDuration)));
  }

  public Map<User, List<Movie>> getWatchedMoviesByUser(String userId) throws UserNotFoundException {
    Map<User, List<Movie>> userMoviesMap = new HashMap<>();

    userMoviesMap.put(dataRepository.users.stream()
        .filter(user -> user.getId().equalsIgnoreCase(userId))
        .findFirst()
        .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID " + userId)),
        dataRepository.viewings.stream()
            .filter(viewing -> viewing.getUser().getId().equalsIgnoreCase(userId))
            .map(viewing -> viewing.getMovie())
            .toList());

    return userMoviesMap;
  }

  public List<Map.Entry<Genre, Long>> getMostWatchedGenre() {
    return dataRepository.viewings.stream()
        .collect(Collectors.groupingBy(viewing -> viewing.getMovie().getGenre(), Collectors.counting()))
        .entrySet()
        .stream()
        .max(Map.Entry.comparingByValue())
        .stream()
        .toList();
  }
}
