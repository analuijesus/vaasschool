package br.com.vaasschool.controller.api.form.validator;

import br.com.vaasschool.controller.form.SubcategoryForm;
import br.com.vaasschool.controller.form.validator.SubcategoryFormValidator;
import br.com.vaasschool.repository.SubcategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SubcategoryFormValidatorTest {

    private SubcategoryRepository subcategoryRepository;
    private SubcategoryFormValidator subcategoryFormValidator;
    private Errors errors;

    @BeforeEach
    public void setUp() {
        subcategoryRepository = mock(SubcategoryRepository.class);
        subcategoryFormValidator = new SubcategoryFormValidator(subcategoryRepository);
        errors = mock(Errors.class);
    }

    @Test
    void shouldGiveErrorWhenCategoryCodeExists() {
        SubcategoryForm form = new SubcategoryForm();
        form.setCode("java-e-persistencia");

        when(subcategoryRepository.existsByCode("java-e-persistencia")).thenReturn(true);

        subcategoryFormValidator.validate(form, errors);
        verify(errors).rejectValue("code", "subcategory.code.existing");
    }

    @Test
    void shouldAcceptTheCategoryWhenTheCodeDoesNotExist() {
        SubcategoryForm form = new SubcategoryForm();
        form.setCode("java-e-persistencia");

        subcategoryFormValidator.validate(form, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldAcceptWhenCodeAndTheIdAreSameAsExisting() {
        when(subcategoryRepository.existsByCodeWithDifferentId(eq("persistencia-com-net"), not(eq(1L)))).thenReturn(true);

        SubcategoryForm form = new SubcategoryForm();
        form.setId(1L);
        form.setCode("persistencia-com-net");

        subcategoryFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldGiveErrorWhenTheNameIsEqualButTheIdDifferent() {
        when(subcategoryRepository.existsByCodeWithDifferentId(eq("persistencia-com-net"), not(eq(1L)))).thenReturn(true);

        SubcategoryForm form = new SubcategoryForm();
        form.setId(999L);
        form.setCode("persistencia-com-net");

        subcategoryFormValidator.validate(form, errors);

        verify(errors).rejectValue("code", "subcategory.code.existing");
    }

    @Test
    void shouldAcceptWhenCodeIsDifferentButIdIsTheEqual() {
        when(subcategoryRepository.existsByCodeWithDifferentId(eq("persistencia-com-net"), not(eq(1L)))).thenReturn(true);

        SubcategoryForm form = new SubcategoryForm();
        form.setId(1L);
        form.setCode("java-api");

        subcategoryFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldAcceptWhenCodeAndIdAreDifferent() {
        when(subcategoryRepository.existsByCodeWithDifferentId(eq("java-api"), not(eq(1L)))).thenReturn(true);

        SubcategoryForm form = new SubcategoryForm();
        form.setId(99L);
        form.setCode("persistencia-com-net");

        subcategoryFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
