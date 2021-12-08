package br.com.vaaschool.model;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private Subcategory subcategory;
    private Category category = new Category("Programação");

    @BeforeEach
    public void setUp() {
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
        assertEquals("Cursos de Java", course.getSubcategory().getName());
        assertEquals("cursos-de-java", course.getSubcategory().getCode());
        assertEquals("Formação Java", course.getSubcategory().getDescription());
        assertEquals(true, course.getSubcategory().getActive());
        assertEquals(1, course.getSubcategory().getOrder());
        assertEquals("Programação", course.getSubcategory().getCategory().getName());
    }

    @Test
    void shouldAcceptWhenTheCodeIsNumber() {
        assertDoesNotThrow(() -> new Course("Java moderno: Tire proveito dos novos recursos do Java 8",
                "java8-lambda", 10, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldAcceptTheValueOneBeingTheSmallestValueForTheEstimatedTimeToFinish() {
        assertDoesNotThrow(() -> new Course("Java moderno: Tire proveito dos novos recursos do Java 8",
                "java-oito-lambda", 1, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldAcceptTheValueTwentyBeingTheHighestValueForTheEstimatedTimeToFinish() {
        assertDoesNotThrow(() -> new Course("Java moderno: Tire proveito dos novos recursos do Java 8",
                "java-oito-lambda", 20, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Course(null, "java-oito-lambda",
                10, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Course("", "java-oito-lambda",
                10, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", null, 6, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "", 8, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeHasCapitalLetters() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "JAVA", 16, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeHasSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "java*", 14, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeContainsWordsWithAccents() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "javaé", 12, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheCodeHasSpace() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "java oito", 10, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheEstimatedTimeToFinishIsLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "java-oito-lambda", 0, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenTheEstimatedTimeToFinishIsGreaterThanTwenty() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "java-oito-lambda", 21, "Ana de Jesus", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenInstructorNameIsEmpty (){
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "java-oito-lambda", 18, "", subcategory));
    }

    @Test
    void shouldThrowAnExceptionWhenInstructorNameIsNull (){
        assertThrows(IllegalArgumentException.class, () -> new Course("Java moderno: Tire proveito dos novos " +
                "recursos do Java 8", "java-oito-lambda", 14, null, subcategory));
    }
}
