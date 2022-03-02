package br.com.vaasschool.controller.api.form.validator;

import br.com.vaasschool.controller.form.CategoryForm;
import br.com.vaasschool.controller.form.validator.CategoryFormValidator;
import br.com.vaasschool.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

public class CategoryFormValidatorTest {

    private CategoryRepository categoryRepository;
    private CategoryFormValidator categoryFormValidator;
    private Errors errors;

    @BeforeEach
    public void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        categoryFormValidator = new CategoryFormValidator(categoryRepository);
        errors = mock(Errors.class);
    }

    @Test
    void ShouldGiveErrorWhenCategoryCodeExists() {
        CategoryForm form = new CategoryForm();
        form.setCode("programacao");

        when(categoryRepository.existsByCode("programacao")).thenReturn(true);

        categoryFormValidator.validate(form, errors);
        verify(errors).rejectValue("code", "code.of.the.new.existing.category");
    }

    @Test
    void shouldAcceptTheCategoryWhenTheCodeDoesNotExist() {
        CategoryForm form = new CategoryForm();
        form.setCode("programacao");

        categoryFormValidator.validate(form, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldAllowWhenCodeAndTheIdAreSameAsExisting() {
        when(categoryRepository.existsByCodeWithDifferentId(eq("devops"), not(eq(1L)))).thenReturn(true);

        CategoryForm form = new CategoryForm();
        form.setId(1L);
        form.setCode("devops");

        categoryFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

//    @Test
//    void shouldGiveErrorWhenTheNameIsEqualButTheIdDifferent() {
//        when(categoryRepository.existsByCodeWithDifferentId(eq("devops"), not(eq(1L)))).thenReturn(true);
//
//        CategoryForm form = new CategoryForm();
//        form.setId(999L);
//        form.setCode("devops");
//
//        categoryFormValidator.validate(form, errors);
//
//        verify(errors).rejectValue("code", "existing.updated.category.code");
//    }

    @Test
    void shouldAcceptWhenCodeIsDifferentButIdIsTheEqual() {
        when(categoryRepository.existsByCodeWithDifferentId(eq("devops"), not(eq(1L)))).thenReturn(true);

        CategoryForm form = new CategoryForm();
        form.setId(1L);
        form.setCode("programacao");

        categoryFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldAcceptWhenCodeAndIdAreDifferent() {
        when(categoryRepository.existsByCodeWithDifferentId(eq("devops"), not(eq(1L)))).thenReturn(true);

        CategoryForm form = new CategoryForm();
        form.setId(99L);
        form.setCode("programacao");

        categoryFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
