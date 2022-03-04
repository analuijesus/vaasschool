package br.com.vaasschool.controller.form.validator;

import br.com.vaasschool.controller.form.SubcategoryForm;
import br.com.vaasschool.repository.SubcategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class SubcategoryFormValidator implements Validator {

    private final SubcategoryRepository subcategoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SubcategoryForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SubcategoryForm form = (SubcategoryForm) target;

        if (form.getId() != null) {
            if (subcategoryRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
                errors.rejectValue("code", "subcategory.code.existing");
            }
        } else {
            if (subcategoryRepository.existsByCode(form.getCode())) {
                errors.rejectValue("code", "subcategory.code.existing");
            }
        }
    }
}