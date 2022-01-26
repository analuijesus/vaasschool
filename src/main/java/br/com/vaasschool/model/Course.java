package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import javax.persistence.*;

@Entity
public class Course {

    public static final int MIN_VALUE_FOR_ESTIMATED_TIME_TO_FINISH = 1;
    public static final int MAX_VALUE_FOR_ESTIMATED_TIME_TO_FINISH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @Column(name = "estimated_time_to_finish", columnDefinition = "smallint")
    private int estimatedTimeToFinish;

    @Column(columnDefinition = "ENUM")
    @Enumerated(EnumType.STRING)
    private CourseVisibility visibility = CourseVisibility.PRIVATE;

    @Column(name = "target_audience")
    private String targetAudience;

    @Column(name = "instructor_name")
    private String instructorName;

    @Column(columnDefinition = "text")
    private String summary;

    @Column(name = "learned_skills")
    private String learnedSkills;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subcategory subcategory;

    @Deprecated
    public Course() {
    }

    public Course(String name, String code, int estimatedTimeToFinish, String instructorName, Subcategory subcategory) {
        Validator.notNullOrEmpty(name, "O nome do curso precisa ser preenchido.");
        Validator.isCode(code, "Insira um código válido.Deve conter apenas letras minúsculas, números e hífen (-).");
        Validator.notNullOrEmpty(instructorName, "O nome do instrutor deve ser preenchido.");
        Validator.notNull(subcategory, "O curso deve ter uma subcategoria associada.");
        Validator.validInterval(estimatedTimeToFinish, MIN_VALUE_FOR_ESTIMATED_TIME_TO_FINISH, MAX_VALUE_FOR_ESTIMATED_TIME_TO_FINISH,
                "Carga horária inválida. Deve estar entre " + MIN_VALUE_FOR_ESTIMATED_TIME_TO_FINISH + " e " +
                        MAX_VALUE_FOR_ESTIMATED_TIME_TO_FINISH);

        this.name = name;
        this.code = code;
        this.estimatedTimeToFinish = estimatedTimeToFinish;
        this.instructorName = instructorName;
        this.subcategory = subcategory;
    }

    public Course(String name, String code, int estimatedTimeToFinish, CourseVisibility visibility, String targetAudience, String instructorName, String summary, String learnedSkills, Subcategory subcategory) {
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

    public boolean isPublic(){
       return CourseVisibility.PUBLIC.equals(visibility);
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

    public void publish() {
        this.visibility = CourseVisibility.PUBLIC;
    }
}
