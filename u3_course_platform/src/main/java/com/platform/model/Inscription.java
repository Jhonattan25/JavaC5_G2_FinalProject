package com.platform.model;

import java.time.LocalDate;

public class Inscription {
  private Estudent estudent;
  private Course course;
  private LocalDate inscriptionDate;

  public Inscription(Estudent estudent, Course course) {
    this.estudent = estudent;
    this.course = course;
    this.inscriptionDate = LocalDate.now();
  }

  public Estudent getEstudent() {
    return estudent;
  }

  public Course getCourse() {
    return course;
  }

  public LocalDate getInscriptionDate() {
    return inscriptionDate;
  }

  @Override
  public String toString() {
    return String.format("""
        ==== INFORMACIÓN DE LA INSCRIPCIÓN ====
        Estudiante: %s
        Curso: %s
        Fecha de incrpción: %s
        """, this.estudent.getName(), this.course.getName(), this.inscriptionDate);
  }
}
