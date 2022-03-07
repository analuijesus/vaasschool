package br.com.vaasschool.util.builder;

import br.com.vaasschool.controller.model.Course;
import br.com.vaasschool.controller.model.CourseVisibility;
import br.com.vaasschool.controller.model.Subcategory;

public class CourseBuilder {

    private String name;
    private String code;
    private int estimatedTimeToFinish;
    private CourseVisibility visibility = CourseVisibility.PRIVATE;
    private String targetAudience;
    private String instructorName;
    private String summary;
    private String learnedSkills;
    private Subcategory subcategory;

    public CourseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CourseBuilder withEstimatedTimeToFinish(int estimatedTimeToFinish) {
        this.estimatedTimeToFinish = estimatedTimeToFinish;
        return this;
    }

    public CourseBuilder withVisibility(CourseVisibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public CourseBuilder withTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
        return this;
    }

    public CourseBuilder withInstructorName(String instructorName) {
        this.instructorName = instructorName;
        return this;
    }

    public CourseBuilder withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public CourseBuilder withLearnedSkills(String learnedSkills) {
        this.learnedSkills = learnedSkills;
        return this;
    }

    public CourseBuilder withSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public Course create() {
        return new Course(name, code, estimatedTimeToFinish, visibility, targetAudience, instructorName, summary, learnedSkills, subcategory);
    }
}