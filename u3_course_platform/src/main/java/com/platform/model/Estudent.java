package com.platform.model;

public class Estudent {
  private String id;
  private String name;
  private String email;

  public Estudent(String id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return String.format("""
        ==== INFORMACIÓN DEL ESTUDIANTE ====
        ID: %s
        Nombre: %s
        Correo: %s
        """, this.id, this.name, this.email);
  }
}
