package com.platform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.platform.exception.CourseNotFoundException;
import com.platform.service.CourseService;

public class CourseServiceTest {

  private CourseService courseService;

  @BeforeEach
  void setUp() {
    courseService = new CourseService();
  }

  @Test
  void testAddCourseSuccess() {
    courseService.addCourse("Mathematics", "MATH101", 30);
    courseService.addCourse("Physics", "PHYS101", 25);

    assertEquals(2, courseService.getCourses().size());
    assertEquals("Mathematics", courseService.getCourses().get(0).getName());
  }

  @Test
  void testEmptyCourseList() {
    assertEquals(0, courseService.getCourses().size());
  }

  @Test
  void testSearchCourseByCodeSuccess() throws Exception {
    courseService.addCourse("Chemistry", "CHEM101", 20);
    assertEquals("Chemistry", courseService.searchCourseByCode("CHEM101").getName());
  }

  @Test
  void testSearchCourseByCodeNotFound() {
    courseService.addCourse("Biology", "BIO101", 15);
    try {
      courseService.searchCourseByCode("HIST101");
    } catch (Exception e) {

      assertThrows(CourseNotFoundException.class, () -> courseService.searchCourseByCode("HIST101"));
      assertEquals("Curso con código HIST101 no fue encontrado", e.getMessage());
    }
  }

}
