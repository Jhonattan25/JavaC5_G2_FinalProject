package com.platform.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.platform.exception.CourseNotFoundException;
import com.platform.model.Course;

public class CourseService {
  private static final Logger logger = LogManager.getLogger(CourseService.class);

  private List<Course> courses; 

  public CourseService() {
    this.courses = new ArrayList<>();
  }

  public void addCourse(String name, String code, int capacity) {
    Course course = new Course(name, code, capacity);
    logger.info("Adding course: " + course.getName());
    courses.add(course);
  }

  public List<Course> getCourses() {
    logger.info("Retrieving list of courses");
    return courses;
  }

  public Course searchCourseByCode(String code) throws CourseNotFoundException {
    for (Course course : courses) {
      if (course.getCode().equals(code)) {
        logger.info("Course found: " + course.getName());
        return course;
      }
    }
    logger.warn("Course not found for code: " + code);
    throw new CourseNotFoundException("Curso con código " + code + " no fue encontrado");
  }
}
