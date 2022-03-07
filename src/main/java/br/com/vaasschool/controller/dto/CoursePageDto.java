package br.com.vaasschool.controller.dto;

import br.com.vaasschool.controller.model.Course;
import lombok.Getter;

@Getter
public class CoursePageDto {

    final private String name;
    final private int estimatedTimeToFinish;

    public CoursePageDto(Course course) {
        this.name = course.getName();
        this.estimatedTimeToFinish = course.getEstimatedTimeToFinish();
    }
}
