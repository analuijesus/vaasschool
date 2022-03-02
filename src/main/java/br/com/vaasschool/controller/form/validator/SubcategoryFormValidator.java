package br.com.vaasschool.controller.form.validator;

import br.com.vaasschool.controller.form.SubcategoryForm;
import br.com.vaasschool.repository.SubcategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SubcategoryFormValidator implements Validator {

    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryFormValidator(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SubcategoryForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SubcategoryForm form = (SubcategoryForm) target;

        if (subcategoryRepository.existsByCode(form.getCode())) {
            errors.rejectValue("code", "code.of.the.new.existing.subcategory");
        }
    }
}