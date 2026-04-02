package com.devsenior.service;

import java.util.ArrayList;
import java.util.List;

import com.devsenior.model.Genre;
import com.devsenior.model.Movie;
import com.devsenior.model.User;
import com.devsenior.model.Viewing;

public class DataRepository {

  public List<User> users = new ArrayList<>();
  public List<Movie> movies = new ArrayList<>();
  public List<Viewing> viewings = new ArrayList<>();

  public DataRepository() {
    User user1 = new User("001", "Juan");
    User user2 = new User("002", "Andrew");
    User user3 = new User("003", "Carlos");
    User user4 = new User("004", "Camilo");
    User user5 = new User("005", "Laura");

    users.add(user1);
    users.add(user2);
    users.add(user3);
    users.add(user4);
    users.add(user5);

    Movie movie1 = new Movie("001", "Matrix", Genre.ACCION, 134);
    Movie movie2 = new Movie("002", "Avenders", Genre.ACCION, 174);
    Movie movie3 = new Movie("003", "Titanic", Genre.DRAMA, 150);
    Movie movie4 = new Movie("004", "Rambo", Genre.ACCION, 110);
    Movie movie5 = new Movie("005", "Anabel", Genre.TERROR, 145);
    Movie movie6 = new Movie("006", "Scary movie", Genre.COMEDIA, 114);
    Movie movie7 = new Movie("007", "Jhon Wick", Genre.ACCION, 134);

    movies.add(movie1);
    movies.add(movie2);
    movies.add(movie3);
    movies.add(movie4);
    movies.add(movie5);
    movies.add(movie6);
    movies.add(movie7);

    Viewing viewing1 = new Viewing(user1, movie1, 130);
    Viewing viewing2 = new Viewing(user2, movie3, 148);
    Viewing viewing3 = new Viewing(user2, movie5, 110);
    Viewing viewing4 = new Viewing(user4, movie4, 109);
    Viewing viewing5 = new Viewing(user1, movie7, 120);
    Viewing viewing6 = new Viewing(user2, movie1, 130);
    Viewing viewing7 = new Viewing(user5, movie6, 110);
    Viewing viewing8 = new Viewing(user5, movie3, 140);
    Viewing viewing9 = new Viewing(user3, movie1, 130);
    Viewing viewing10 = new Viewing(user2, movie6, 110);

    viewings.add(viewing1);
    viewings.add(viewing2);
    viewings.add(viewing3);
    viewings.add(viewing4);
    viewings.add(viewing5);
    viewings.add(viewing6);
    viewings.add(viewing7);
    viewings.add(viewing8);
    viewings.add(viewing9);
    viewings.add(viewing10);
  }
}
