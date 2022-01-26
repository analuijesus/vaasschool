package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;

import java.util.List;

public class SubcategoryApiDto {

    private String name;
    private String code;
    private String explanatoryGuide;
    private List<CourseApiDto> coursesApi;

    public SubcategoryApiDto(Subcategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.explanatoryGuide = subcategory.getExplanatoryGuide();
        this.coursesApi = subcategory.getCourses().stream()
                .filter(Course::isPublic)
                .map(CourseApiDto::new).toList();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getExplanatoryGuide() {
        return explanatoryGuide;
    }

    public List<CourseApiDto> getCoursesApi() {
        return coursesApi;
    }
}