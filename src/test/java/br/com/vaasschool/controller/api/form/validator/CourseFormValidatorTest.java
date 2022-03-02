package br.com.vaasschool.controller.api.form.validator;

import br.com.vaasschool.controller.form.CourseForm;
import br.com.vaasschool.controller.form.validator.CourseFormValidator;
import br.com.vaasschool.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CourseFormValidatorTest {

    private CourseRepository courseRepository;
    private CourseFormValidator courseFormValidator;
    private Errors errors;

    @BeforeEach
    public void setUp() {
        courseRepository = mock(CourseRepository.class);
        courseFormValidator = new CourseFormValidator(courseRepository);
        errors = mock(Errors.class);
    }

    @Test
    void ShouldGiveErrorWhenCourseCodeExists() {
        CourseForm form = new CourseForm();
        form.setCode("mocks-java-mockito");

        when(courseRepository.existsByCode("mocks-java-mockito")).thenReturn(true);

        courseFormValidator.validate(form, errors);
        verify(errors).rejectValue("code", "code.of.the.new.existing.course");
    }

    @Test
    void shouldAcceptTheCourseWhenTheCodeDoesNotExist() {
        CourseForm form = new CourseForm();
        form.setCode("programacao");

        courseFormValidator.validate(form, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldAllowWhenCodeAndTheIdAreEqualAsExisting() {
        when(courseRepository.existsByCodeWithDifferentId(eq("mocks-java-mockito"), not(eq(1L)))).thenReturn(true);

        CourseForm form = new CourseForm();
        form.setId(1L);
        form.setCode("mocks-java-mockito");

        courseFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

//    @Test
//    void shouldGiveErrorWhenTheNameIsEqualButTheIdDifferent() {
//        when(categoryRepository.existsByCodeWithDifferentId(eq("mocks-java-mockito"), not(eq(1L)))).thenReturn(true);
//
//        CourseForm form = new CourseForm();
//        form.setId(999L);
//        form.setCode("mocks-java-mockito");
//
//        courseFormValidator.validate(form, errors);
//
//        verify(errors).rejectValue("code", "existing.updated.course.code");
//    }

    @Test
    void shouldAcceptWhenCodeIsDifferentButIdIsTheEqual() {
        when(courseRepository.existsByCodeWithDifferentId(eq("mocks-java-mockito"), not(eq(1L)))).thenReturn(true);

        CourseForm form = new CourseForm();
        form.setId(1L);
        form.setCode("python-3-avancando-na-linguagem");

        courseFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldAcceptWhenCodeAndIdAreDifferent() {
        when(courseRepository.existsByCodeWithDifferentId(eq("mocks-java-mockito"), not(eq(1L)))).thenReturn(true);

        CourseForm form = new CourseForm();
        form.setId(99L);
        form.setCode("python-3-avancando-na-linguagem");

        courseFormValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}
