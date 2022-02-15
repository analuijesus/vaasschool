package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;

import java.util.List;

public class SubcategoryPageDto {

    private final String name;
    private final String code;
    private final List<CoursePageDto> coursePageDtos;

    public SubcategoryPageDto(Subcategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.coursePageDtos = subcategory.getCourses().stream().filter(Course::isPublic).map(CoursePageDto::new).toList();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<CoursePageDto> getCoursePageDtos() {
        return coursePageDtos;
    }
}
