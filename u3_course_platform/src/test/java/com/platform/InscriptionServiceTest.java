package com.platform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.platform.exception.CourseNotFoundException;
import com.platform.exception.EstudentNotFoundException;
import com.platform.exception.FullCourseException;
import com.platform.model.Estudent;
import com.platform.service.CourseService;
import com.platform.service.InscriptionService;

public class InscriptionServiceTest {

  private InscriptionService inscriptionService;

  @BeforeEach
  void setUp() {
    inscriptionService = new InscriptionService();
  }

  @Test
  void testInscriptionEstudentSuccess() throws CourseNotFoundException {
    CourseService courseService = new CourseService();
    courseService.addCourse("Mathematics", "MATH101", 30);
    Estudent estudent = new Estudent("001", "John Doe", "john.doe@example.com");

    inscriptionService.inscriptionEstudent(courseService.searchCourseByCode("MATH101"), estudent);

    assertEquals(1, inscriptionService.getInscriptions().size());
  }

  @Test
  void testInscriptionEstudentCourseFull() throws CourseNotFoundException {
    CourseService courseService = new CourseService();
    courseService.addCourse("Physics", "PHYS101", 1);
    Estudent estudent1 = new Estudent("002", "Jane Doe", "jane.doe@example.com");
    Estudent estudent2 = new Estudent("003", "Bob Smith", "bob.smith@example.com");

    inscriptionService.inscriptionEstudent(courseService.searchCourseByCode("PHYS101"), estudent1);

    try {
      inscriptionService.inscriptionEstudent(courseService.searchCourseByCode("PHYS101"), estudent2);
    } catch (Exception e) {
      assertThrows(FullCourseException.class, () -> inscriptionService.inscriptionEstudent(
          courseService.searchCourseByCode("PHYS101"), estudent2));
      assertEquals("El curso Physics está lleno", e.getMessage());
    }
  }

  @Test
  void testGetInscriptionsEmpty() {
    assertEquals(0, inscriptionService.getInscriptions().size());
  }

  @Test
  void testGetInscriptionsByEstudentSuccess() throws CourseNotFoundException, EstudentNotFoundException {
    CourseService courseService = new CourseService();
    courseService.addCourse("Chemistry", "CHEM101", 20);
    Estudent estudent = new Estudent("001", "John Doe", "john.doe@example.com");

    inscriptionService.inscriptionEstudent(courseService.searchCourseByCode("CHEM101"), estudent);
    assertEquals(1, inscriptionService.geInscriptionsByEstudent(estudent).size());
  }

  @Test
  void testGetInscriptionsByEstudentNotFound() {
    Estudent estudent = new Estudent("004", "Alice Johnson", "alice.johnson@example.com");
    try {
      inscriptionService.geInscriptionsByEstudent(estudent);
    } catch (Exception e) {
      assertThrows(EstudentNotFoundException.class, () -> inscriptionService.geInscriptionsByEstudent(estudent));
      assertEquals("Inscripciones no encontradas para el estudiante Alice Johnson", e.getMessage());
    }
  }

}
