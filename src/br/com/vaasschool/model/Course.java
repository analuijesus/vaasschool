package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public class Course {

    public static final int MIN_VALUE_FOR_ESTIMATED_TIME_TO_FINISH = 1;
    public static final int MAX_VALUE_FOR_ESTIMATED_TIME_TO_FINISH = 20;

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
        Validator.notNullOrEmpty(name, "O nome do curso precisa ser preenchido.");
        Validator.isCode(code, "Insira um código válido.Deve conter apenas letras minúsculas, números e hífen (-).");
        Validator.notNullOrEmpty(instructorName,"O nome do instrutor deve ser preenchido.");
        Validator.notNull(subcategory,"O curso deve ter uma subcategoria associada.");
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

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
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
