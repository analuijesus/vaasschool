package br.com.vaasschool.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private Subcategory subcategory;

    @BeforeEach
    public void setUp() {
        Category category = new Category("Programação", "formacao-java");
        subcategory = new Subcategory("Cursos de Java", "cursos-de-java", "Formação Java",
                true, 1, category);
    }

    @Test
    void shouldAddNewCourse() {
        Course course = assertDoesNotThrow(() -> new Course("Java moderno: Tire proveito dos novos recursos " +
                "do Java 8", "java-oito-lambda", 1, "Ana de Jesus", subcategory));

        assertEquals("Java moderno: Tire proveito dos novos recursos do Java 8", course.getName());
        assertEquals("java-oito-lambda", course.getCode());
        assertEquals(1, course.getEstimatedTimeToFinish());
        assertEquals("Ana de Jesus", course.getInstructorName());
        assertEquals(subcategory, course.getSubcategory());
    }

    @Test
    void shouldAcceptWhenTheCodeContainsNumber() {
        assertDoesNotThrow(() -> new Course("Java moderno: Tire proveito dos novos recursos do Java 8",
                "java8-lambda", 10, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldAcceptTheSmallestValueForTheEstimatedTimeToFinish() {
        assertDoesNotThrow(() -> new Course("Java moderno: Tire proveito dos novos recursos do Java 8",
                "java-oito-lambda", 1, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldAcceptTheHighestValueForTheEstimatedTimeToFinish() {
        assertDoesNotThrow(() -> new Course("Java moderno: Tire proveito dos novos recursos do Java 8",
                "java-oito-lambda", 20, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheEstimatedTimeToFinishIsLessThanTheSmallest() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "java-oito-lambda", 0, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheEstimatedTimeToFinishIsGreaterThanTheHighest() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "java-oito-lambda", 21, "Ana de Jesus", subcategory));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowAnExceptionWhenTheNameIsNullOrEmpty(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Course(name, "java-oito-lambda",
                10, "Ana de Jesus", subcategory));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowAnExceptionWhenInstructorNameIsNullOrEmpty(String instructorName) {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "java-oito-lambda", 14, instructorName, subcategory));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowAnExceptionWhenTheCodeIsNullOrEmpty(String code) {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", code, 10, "Ana de Jesus", subcategory));
    }

    @ParameterizedTest
    @CsvSource({"JAVA", "java*", "javaé", "java oito lambda"})
    void shouldThrowAnExceptionWhenTheCodeIsInvalid(String invalidCode) {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", invalidCode, 10, "Ana de Jesus", subcategory));
    }
}
