package br.com.vaasschool.controller.form.validator;

import br.com.vaasschool.controller.form.CourseForm;
import br.com.vaasschool.repository.CourseRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CourseFormValidator implements Validator {

    private final CourseRepository courseRepository;

    public CourseFormValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CourseForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseForm form = (CourseForm) target;

        if (form.getId() != null) {
            if (courseRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
                errors.rejectValue("code", "existing.updated.course.code");
            }
        } else {
            if (courseRepository.existsByCode(form.getCode())) {
                errors.rejectValue("code", "code.of.the.new.existing.course");
            }
        }
    }
}
