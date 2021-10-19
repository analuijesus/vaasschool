package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public class Course {

    private String name;
    private String code;
    private int estimatedTimeToFinish;
    private CourseVisibility visibility = CourseVisibility.PRIVATE;
    private String targetAudience;
    private String instructorName;
    private String summary;
    private String learnedSkills;
    private Subcategory subcategory;

    public Course(String name, String code, int estimatedTimeToFinish, String instructorName, Subcategory subcategory) {

        Validator.writtenFieldValidation(name);
        Validator.codeValidation(code);
        Validator.writtenFieldValidation(instructorName);
        Validator.nullValidation(subcategory);

        if (estimatedTimeToFinish < 1 || estimatedTimeToFinish > 20){
            throw new IllegalArgumentException("Carga horária inválida");
        }

        this.name = name;
        this.code = code;
        this.estimatedTimeToFinish = estimatedTimeToFinish;
        this.instructorName = instructorName;
        this.subcategory = subcategory;
    }

    public CourseVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(CourseVisibility visibility) {
        this.visibility = visibility;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setLearnedSkills(String learnedSkills) {
        this.learnedSkills = learnedSkills;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getEstimatedTimeToFinish() {
        return estimatedTimeToFinish;
    }

    public String getTargetAudience() {
        return targetAudience;
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
}
