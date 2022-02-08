package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;

public class CourseDto {

    final private Long id;
    final private String name;
    final private String code;
    final private CourseVisibility visibility;

    public CourseDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
        this.visibility = course.getVisibility();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public CourseVisibility getVisibility() {
        return visibility;
    }

}
