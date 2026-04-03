package com.devsenior.service;

import java.security.KeyStore.Entry;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  public Map<User, Integer> getwatchedDurationByUser() {
    return dataRepository.viewings.stream()
        .collect(Collectors.groupingBy(Viewing::getUser, Collectors.summingInt(Viewing::getWatchedDurationMin)));
  }

  public List<Map.Entry<User, Integer>> getTop3Users() {
    return getwatchedDurationByUser().entrySet().stream()
        .sorted(Map.Entry.<User, Integer>comparingByValue().reversed())
        .limit(3)
        .toList();
  }

  public Map<Genre, Double> getAverageMovieDurationByGenre() {
    return dataRepository.movies.stream()
        .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingInt(Movie::getDuration)));
  }
}
