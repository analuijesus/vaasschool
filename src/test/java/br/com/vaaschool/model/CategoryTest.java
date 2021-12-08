package br.com.vaaschool.model;

import br.com.vaasschool.model.Category;
import org.junit.jupiter.api.Test;

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

    @Test
    void shouldThrowAnExceptionWhenTheNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Category(null, "java8-lambda"));
    }

    @Test
    void shouldThrowAnExceptionWhenTheNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Category("", "java8-lambda"));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Category("Programação", null));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Category("Programação", ""));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeHasCapitalLetters() {
        assertThrows(IllegalArgumentException.class, () -> new Category("Programação", "JAVA"));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeHasSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new Category("Programação", "java*"));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeHasWordsWithAccents() {
        assertThrows(IllegalArgumentException.class, () -> new Category("Programação", "javaé"));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeHasSpace() {
        assertThrows(IllegalArgumentException.class, () -> new Category("Programação", "java oito lambda"));
    }
}
