package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;

public class CourseDto {

    final private Long id;
    final private String name;
    final private String code;
    final private CourseVisibility visibility;
    final private int estimatedTimeToFinish;
    final private String targetAudience;
    final private String instructorName;
    final private String summary;
    final private String learnedSkills;
    final private String subcategoryName;

    public CourseDto(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
        this.visibility = course.getVisibility();
        this.estimatedTimeToFinish = course.getEstimatedTimeToFinish();
        this.targetAudience = course.getTargetAudience();
        this.instructorName = course.getInstructorName();
        this.summary = course.getSummary();
        this.learnedSkills = course.getLearnedSkills();
        this.subcategoryName = course.getSubcategoryName();
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

    public String getTargetAudience() {
        return targetAudience;
    }

    public int getEstimatedTimeToFinish() {
        return estimatedTimeToFinish;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getSummary() {
        return summary;
    }

    public String getLearnedSkills() {
        return learnedSkills;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }
}
