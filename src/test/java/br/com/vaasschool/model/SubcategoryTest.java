package br.com.vaasschool.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

public class SubcategoryTest {

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category("Programação", "formacao-java");
    }

    @Test
    void shouldAddNewSubcategory() {
        Subcategory subcategory = assertDoesNotThrow(() -> new Subcategory("Teste utilizando Java",
                "teste-utilizando-java", "A importância de escrever testes automatizados em uma aplicação",
                true, 1, category));
        assertEquals("Teste utilizando Java", subcategory.getName());
        assertEquals("teste-utilizando-java", subcategory.getCode());
        assertEquals("A importância de escrever testes automatizados em uma aplicação", subcategory.getDescription());
        assertEquals(true, subcategory.getActive());
        assertEquals(1, subcategory.getOrder());
        assertEquals(category, subcategory.getCategory());
    }

    @Test
    void shouldAcceptWhenTheCodeContainsNumber() {
        assertDoesNotThrow(() -> new Subcategory("Cursos de Java", "java8-lambda",
                "Formação Java", true, 1, category));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowAnExceptionWhenTheNameIsNullOrEmpty(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(name, "java-oito",
                "novidades do java 8", true, 1, category));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowAnExceptionWhenTheCodeIsNullOrEmpty(String code) {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Teste utilizando Java", code,
                "A importância de escrever testes automatizados em uma aplicação", true,
                1, category));
    }

    @ParameterizedTest
    @CsvSource({"tdd e java", "tdd*e*java*", "javaé", "TDD-e-java"})
    void shouldThrowAnExceptionWhenTheCodeIsInvalid(String invalidCode) {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Teste utilizando Java",
                invalidCode, "A importância de escrever testes automatizados em uma aplicação",
                true, 1, category));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCategoryIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Teste utilizando Java",
                "tdd-e-java", "A importância de escrever testes automatizados em uma aplicação",
                true, 1, null));
    }
}
