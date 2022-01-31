package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Course;

import java.util.List;

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

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getEstimatedTimeToFinishTheCourse() {
        return estimatedTimeToFinishTheCourse;
    }

    public String getSkillsLearnedInTheCourse() {
        return skillsLearnedInTheCourse;
    }
}