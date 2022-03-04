package br.com.vaasschool.controller.form.validator;

import br.com.vaasschool.controller.form.CategoryForm;
import br.com.vaasschool.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class CategoryFormValidator implements Validator {

    private final CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryForm form = (CategoryForm) target;

        if (form.getId() != null) {
            if (categoryRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
                errors.rejectValue("code", "category.code.existing");
            }
        } else {
            if (categoryRepository.existsByCode(form.getCode())) {
                errors.rejectValue("code", "category.code.existing");
            }
        }
    }
}