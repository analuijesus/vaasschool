package br.com.vaasschool.controller.api.dto;

import br.com.vaasschool.model.Course;
import lombok.Getter;

@Getter
public class CourseApiDto {

    final private String name;
    final private String code;
    final private int estimatedTimeToFinishTheCourse;
    final private String skillsLearnedInTheCourse;

    public CourseApiDto(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.estimatedTimeToFinishTheCourse = course.getEstimatedTimeToFinish();
        this.skillsLearnedInTheCourse = course.getLearnedSkills();
    }
}