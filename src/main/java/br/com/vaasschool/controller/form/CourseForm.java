package br.com.vaasschool.controller.form;

import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.repository.CourseRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CourseForm {

    private Long id;
    @NotBlank(message = "O nome do curso precisa ser preenchido.")
    private String name;
    @NotBlank(message = "O código do curso é obrigatório.")
    @Pattern(regexp = "([a-z0-9^-]+)", message = "Insira um código válido. O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;
    @NotNull(message = "Carga horária inválida. Deve estar entre 1 e 20")
    private int estimatedTimeToFinish;
    private CourseVisibility visibility = CourseVisibility.PRIVATE;
    private String targetAudience;
    @NotBlank(message = "O nome do instrutor deve ser preenchido.")
    private String instructorName;
    private String summary;
    private String learnedSkills;
    @NotNull(message = "O curso deve ter uma subcategoria associada.")
    private Long subcategoryId;
    private String subcategoryName;

    public CourseForm() {
    }

    public CourseForm(Course course){
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
        this.estimatedTimeToFinish = course.getEstimatedTimeToFinish();
        this.visibility = course.getVisibility();
        this.targetAudience = course.getTargetAudience();
        this.instructorName = course.getInstructorName();
        this.summary = course.getSummary();
        this.learnedSkills = course.getLearnedSkills();
        this.subcategoryId = course.getSubcategoryId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getEstimatedTimeToFinish() {
        return estimatedTimeToFinish;
    }

    public void setEstimatedTimeToFinish(int estimatedTimeToFinish) {
        this.estimatedTimeToFinish = estimatedTimeToFinish;
    }

    public CourseVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(CourseVisibility visibility) {
        this.visibility = visibility;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLearnedSkills() {
        return learnedSkills;
    }

    public void setLearnedSkills(String learnedSkills) {
        this.learnedSkills = learnedSkills;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public Course toModel(Subcategory subcategory){
        return new Course(name, code, estimatedTimeToFinish, visibility, targetAudience, instructorName, summary, learnedSkills, subcategory);
    }
}
