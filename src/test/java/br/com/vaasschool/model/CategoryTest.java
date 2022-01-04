package br.com.vaasschool.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;
public class CategoryTest {

    @Test
    void shouldAddNewCategory() {
        Category category = assertDoesNotThrow(() -> new Category("Programação", "formacao-java"));
        assertEquals("Programação", category.getName());
        assertEquals("formacao-java", category.getCode());
    }

    @Test
    void shouldAcceptWhenTheCodeContainsNumber() {
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
