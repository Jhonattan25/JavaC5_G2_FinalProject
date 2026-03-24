package com.platform.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
  private String name;
  private String code;
  private int capacity;
  private List<Estudent> estudents;

  public Course(String name, String code, int capacity) {
    this.name = name;
    this.code = code;
    this.capacity = capacity;
    this.estudents = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public List<Estudent> getEstudents() {
    return estudents;
  }

  public void addEstudent(Estudent newEstudent) {
    this.estudents.add(newEstudent);
  }

  public void removeEstudent(Estudent estudent) {
    this.estudents.remove(estudent);
  }

  public boolean isFull() {
    return this.estudents.size() >= capacity;
  }

  @Override
  public String toString() {
    return String.format("""
        ==== INFORMACIÓN DEL CURSO ====
        Codigo: %s
        Nombre: %s
        Capacidad: %s
        """, this.code, this.name, this.capacity);
  }
}
