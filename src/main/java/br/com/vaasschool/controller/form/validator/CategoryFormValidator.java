package br.com.vaasschool.controller.form.validator;

import br.com.vaasschool.controller.form.CategoryForm;
import br.com.vaasschool.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryFormValidator implements Validator {

    private final CategoryRepository categoryRepository;

    public CategoryFormValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryForm form = (CategoryForm) target;

        if (categoryRepository.existsByCode(form.getCode())) {
            errors.rejectValue("code", "code.of.the.new.existing.category");
        }

//        if (categoryRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
//            errors.rejectValue("code", "existing.updated.category.code");
//        }
//        if (categoryRepository.existsByCode(form.getCode()) || categoryRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
//            errors.rejectValue("code", "already.existing.code.testecategoriacode");
//        }
//
//        if (categoryRepository.existsByCode(form.getCode())) {
//            if (categoryRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
//                errors.rejectValue("code", "already.existing.code.testecategoriaid");
//            }
//        }
//        errors.rejectValue("code", "already.existing.code.testecategoriacode");
    }
}