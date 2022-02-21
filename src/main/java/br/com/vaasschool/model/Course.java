package br.com.vaasschool.model;

import br.com.vaasschool.controller.form.CourseForm;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Course {

    public static final int MIN_VALUE_FOR_ESTIMATED_TIME_TO_FINISH = 1;
    public static final int MAX_VALUE_FOR_ESTIMATED_TIME_TO_FINISH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome do curso precisa ser preenchido.")
    private String name;
    @NotBlank(message = "O código do curso é obrigatório.")
    @Pattern(regexp = "([a-z0-9^-]+)", message = "Insira um código válido. O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;
    @NotNull(message = "Carga horária inválida. Deve estar entre " + MIN_VALUE_FOR_ESTIMATED_TIME_TO_FINISH + " e " +
            MAX_VALUE_FOR_ESTIMATED_TIME_TO_FINISH)
    @Column(name = "estimated_time_to_finish", columnDefinition = "smallint")
    private int estimatedTimeToFinish;
//    @Column(columnDefinition = "ENUM")
    @Enumerated(EnumType.STRING)
    private CourseVisibility visibility = CourseVisibility.PRIVATE;
    @Column(name = "target_audience")
    private String targetAudience;
    @Column(name = "instructor_name")
    @NotBlank(message = "O nome do instrutor deve ser preenchido.")
    private String instructorName;
    @Column(columnDefinition = "text")
    private String summary;
    @Column(name = "learned_skills")
    private String learnedSkills;
    @NotNull(message = "O curso deve ter uma subcategoria associada.")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subcategory subcategory;

    @Deprecated
    public Course() {
    }

    public Course(String name, String code, int estimatedTimeToFinish, String instructorName, Subcategory subcategory) {
        this.name = name;
        this.code = code;
        this.estimatedTimeToFinish = estimatedTimeToFinish;
        this.instructorName = instructorName;
        this.subcategory = subcategory;
    }

    public Course(String name, String code, int estimatedTimeToFinish, CourseVisibility visibility, String targetAudience,
                  String instructorName, String summary, String learnedSkills, Subcategory subcategory) {
        this(name, code, estimatedTimeToFinish, instructorName, subcategory);
        this.visibility = visibility;
        this.targetAudience = targetAudience;
        this.summary = summary;
        this.learnedSkills = learnedSkills;
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

    public String getInstructorName() {
        return instructorName;
    }

    public String getSummary() {
        return summary;
    }

    public String getLearnedSkills() {
        return learnedSkills;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public int getEstimatedTimeToFinish() {
        return estimatedTimeToFinish;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPublic() {
        return CourseVisibility.PUBLIC.equals(visibility);
    }

    public void publish() {
        this.visibility = CourseVisibility.PUBLIC;
    }

    public String getSubcategoryName() {
        return subcategory.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEstimatedTimeToFinish(int estimatedTimeToFinish) {
        this.estimatedTimeToFinish = estimatedTimeToFinish;
    }

    public void setVisibility(CourseVisibility visibility) {
        this.visibility = visibility;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setLearnedSkills(String learnedSkills) {
        this.learnedSkills = learnedSkills;
    }

    public String getSubcategoryCode() {
        return subcategory.getCode();
    }

    public Long getSubcategoryId() {
        return subcategory.getId();
    }

    public void update(CourseForm courseForm, Subcategory subcategory) {
        this.id = courseForm.getId();
        this.name = courseForm.getName();
        this.code = courseForm.getCode();
        this.estimatedTimeToFinish = courseForm.getEstimatedTimeToFinish();
        this.visibility = courseForm.getVisibility();
        this.targetAudience = courseForm.getTargetAudience();
        this.instructorName = courseForm.getInstructorName();
        this.learnedSkills = courseForm.getLearnedSkills();
        this.summary = courseForm.getSummary();
        this.subcategory = subcategory;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", estimatedTimeToFinish=" + estimatedTimeToFinish +
                ", visibility=" + visibility +
                ", targetAudience='" + targetAudience + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", summary='" + summary + '\'' +
                ", learnedSkills='" + learnedSkills + '\'' +
                ", subcategory=" + subcategory.getName() +
                '}';
    }
}
