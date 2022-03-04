package br.com.vaasschool.controller.form;

import br.com.vaasschool.controller.model.Course;
import br.com.vaasschool.controller.model.CourseVisibility;
import br.com.vaasschool.controller.model.Subcategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
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

    public Course toModel(Subcategory subcategory){
        return new Course(name, code, estimatedTimeToFinish, visibility, targetAudience, instructorName, summary, learnedSkills, subcategory);
    }
}
