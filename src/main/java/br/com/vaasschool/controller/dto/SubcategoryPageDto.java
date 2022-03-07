package br.com.vaasschool.controller.dto;

import br.com.vaasschool.controller.model.Course;
import br.com.vaasschool.controller.model.Subcategory;
import lombok.Getter;

import java.util.List;

@Getter
public class SubcategoryPageDto {

    final private String name;
    final private String code;
    final private List<CoursePageDto> coursePageDtos;

    public SubcategoryPageDto(Subcategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.coursePageDtos = subcategory.getCourses().stream().filter(Course::isPublic).map(CoursePageDto::new).toList();
    }
}
