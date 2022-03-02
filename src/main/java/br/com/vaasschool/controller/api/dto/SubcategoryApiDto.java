package br.com.vaasschool.controller.api.dto;

import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;
import lombok.Getter;

import java.util.List;

@Getter
public class SubcategoryApiDto {

    final private String name;
    final private String code;
    final private String explanatoryGuide;
    final private List<CourseApiDto> coursesApi;

    public SubcategoryApiDto(Subcategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.explanatoryGuide = subcategory.getExplanatoryGuide();
        this.coursesApi = subcategory.getCourses().stream().filter(Course::isPublic).map(CourseApiDto::new).toList();
    }
}