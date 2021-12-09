package br.com.vaaschool.model;

import br.com.vaasschool.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;
public class CategoryTest {

    @Test
    void shouldAddNewCategory() {
        Category category = assertDoesNotThrow(() -> new Category("Programação", "java-oito"));
        assertEquals("Programação", category.getName());
        assertEquals("java-oito", category.getCode());
    }

    @Test
    void shouldAcceptWhenTheCodeIsNumber() {
        assertDoesNotThrow(() -> new Category("Programação", "java8-lambda"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowAnExceptionWhenTheNameIsNullOrEmpty(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Category(name, "java8-lambda"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowAnExceptionWhenTheCodeIsNullOrEmpty(String code) {
        assertThrows(IllegalArgumentException.class, () -> new Category("Programação", code));
    }

    @ParameterizedTest
    @CsvSource({"JAVA", "java*", "javaé", "java oito lambda"})
    void shouldThrowAnExceptionWhenTheCodeIsInvalid(String invalidCode) {
        assertThrows(IllegalArgumentException.class, () -> new Category("Programação", invalidCode));
    }
}
