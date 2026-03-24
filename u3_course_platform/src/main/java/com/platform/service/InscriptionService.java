package com.platform.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.platform.exception.EstudentNotFoundException;
import com.platform.exception.FullCourseException;
import com.platform.model.Course;
import com.platform.model.Estudent;
import com.platform.model.Inscription;

public class InscriptionService {
  private static final Logger logger = LogManager.getLogger(InscriptionService.class);

  private List<Inscription> inscriptions;

  public InscriptionService() {
    this.inscriptions = new ArrayList<>();
  }

  public void inscriptionEstudent(Course course, Estudent estudent) {
    if (course.isFull()) {
      logger.warn("No se puede inscribir al estudiante " + estudent.getName()
          + " al curso " + course.getName(), " porque esta lleno");
      throw new FullCourseException("El curso " + course.getName() + " está lleno");
    }

    logger.info("Adding inscription: course " + course.getName() + " estudent " + estudent.getName());
    inscriptions.add(new Inscription(estudent, course));
    course.addEstudent(estudent);
  }

  public List<Inscription> getInscriptions() {
    logger.info("Getting inscriptions: " + inscriptions.size() + " inscriptions found");
    return inscriptions;
  }

  public List<Inscription> geInscriptionsByEstiudent(Estudent estudent) throws EstudentNotFoundException {
    List<Inscription> result = new ArrayList<>();
    for (Inscription inscription : inscriptions) {
      if (inscription.getEstudent().getId().equals(estudent.getId())) {
        result.add(inscription);
      }
    }

    if (result.isEmpty()) {
      logger.warn("No inscriptions found for estudent " + estudent.getName());
      throw new EstudentNotFoundException("Inscripciones no encontradas para el estudiante " + estudent.getName());
    }

    logger.info("Getting inscriptions for estudent " + estudent.getName() + ": " + result.size() + " inscriptions found");
    return result;
  }
}
