package com.platform;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.platform.model.Estudent;
import com.platform.service.CourseService;
import com.platform.service.InscriptionService;

public class Main {
  private static final Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) {
    CourseService courseService = new CourseService();
    InscriptionService inscriptionService = new InscriptionService();

    logger.info("Creating courses...");
    courseService.addCourse("Matemáticas", "MATH101", 20);
    courseService.addCourse("Historia", "HIST101", 20);

    logger.info("Creating Estudents");
    Estudent estudent1 = new Estudent("E001", "Juan Pérez", "juan@university.edu");

    logger.info("Inscribing Estudents to courses...");
    
    try {
      inscriptionService.inscriptionEstudent(courseService.searchCourseByCode("MATH101"), estudent1);
    } catch (Exception e) {
      logger.error("Error inscribing estudent: " + e.getMessage());
    }
  }
}