package br.com.vaaschool.model;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubcategoryTest {

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category("Programação");
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
        assertEquals("Programação", subcategory.getCategory().getName());
    }

    @Test
    void shouldAcceptWhenTheCodeIsNumber() {
        assertDoesNotThrow(() -> new Subcategory("Cursos de Java", "java8-lambda",
                "Formação Java", true, 1, category));
    }

    @Test
    void shouldThrowAnExceptionWhenTheNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(null, "java-oito",
                "novidades do java 8", true, 1, category));
    }

    @Test
    void shouldThrowAnExceptionWhenTheNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("", "java-oito",
                "novidades do java 8", true, 1, category));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Teste utilizando Java",
                null, "A importância de escrever testes automatizados em uma aplicação", true,
                1, category));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Teste utilizando Java", "",
                "A importância de escrever testes automatizados em uma aplicação", true,
                1, category));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeHasCapitalLetters() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Teste utilizando Java",
                "TDD-e-java", "A importância de escrever testes automatizados em uma aplicação",
                true, 1, category));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeHasSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Teste utilizando Java",
                "tdd*e*java", "A importância de escrever testes automatizados em uma aplicação",
                true, 1, category));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeContainsWordsWithAccents() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Teste utilizando Java",
                "javaé", "A importância de escrever testes automatizados em uma aplicação",
                true, 1, category));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeHasSpace() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Teste utilizando Java",
                "tdd e java", "A importância de escrever testes automatizados em uma aplicação",
                true, 1, category));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCategoryIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Teste utilizando Java",
                "tdd-e-java", "A importância de escrever testes automatizados em uma aplicação",
                true, 1, null));
    }
}
