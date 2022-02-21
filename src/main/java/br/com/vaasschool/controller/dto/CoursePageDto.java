package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Course;

public class CoursePageDto {

    final private String name;
    final private int estimatedTimeToFinish;

    public CoursePageDto(Course course) {
        this.name = course.getName();
        this.estimatedTimeToFinish = course.getEstimatedTimeToFinish();
    }

    public String getName() {
        return name;
    }

    public int getEstimatedTimeToFinish() {
        return estimatedTimeToFinish;
    }
}
