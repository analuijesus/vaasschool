package br.com.vaasschool.repository;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.projection.CategoryProjection;
import br.com.vaasschool.util.builder.CategoryBuilder;
import br.com.vaasschool.util.builder.CourseBuilder;
import br.com.vaasschool.util.builder.SubcategoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static br.com.vaasschool.model.CourseVisibility.PRIVATE;
import static br.com.vaasschool.model.CourseVisibility.PUBLIC;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldBringAllByOrderWhenTheCategoryIsActiveAndSubcategoryActiveAndPublicCourse() {
        aCourse("java-jdbc", PUBLIC,
                aSubcategory("java", true,
                        aCategory("programacao", true)));
        aCourse("python-estatistica", PUBLIC,
                aSubcategory("python", true,
                        aCategory("data-science", true)));
        aCourse("java-mobile", PUBLIC,
                aSubcategory("dart", true,
                        aCategory("mobile", false)));
        aCourse("react", PRIVATE,
                aSubcategory("javascript", true,
                        aCategory("front-end", true)));

        List<Category> categories = categoryRepository.findByActiveCategoryAndActiveSubcategoryAndPublicCourse();

        assertThat(categories)
                .hasSize(2)
                .extracting(Category::getCode, Category::getActive)
                .containsExactly(
                        tuple("programacao", true),
                        tuple("data-science", true)
                );
    }

    @Test
    public void shouldModifyActiveToFalseWhenActiveIsTrue() {
        Category category = aCategory("programacao", true);

        categoryRepository.setActiveFalse(category.getId());
        entityManager.clear();
        Optional<Category> possibleCategory = categoryRepository.findById(category.getId());

        assertThat(possibleCategory.get().getActive())
                .isFalse();
    }

    @Test
    public void shouldKeepAsFalseWhenActiveIsFalse() {
        Category category = aCategory("programacao", false);

        categoryRepository.setActiveFalse(category.getId());
        entityManager.clear();
        Optional<Category> possibleCategory = categoryRepository.findById(category.getId());

        assertThat(possibleCategory.get().getActive())
                .isFalse();
    }

    @Test
    public void shouldBringTheNameOfTheCategoryWithAmountOfCourses() {
        aCourse("java-jdbc", PUBLIC,
                aSubcategory("java", true,
                        aCategory("programacao", true)));
        aCourse("python-estatistica", PUBLIC,
                aSubcategory("python", true,
                        aCategory("data-science", true)));
        aCourse("java-mobile", PUBLIC,
                aSubcategory("dart", true,
                        aCategory("mobile", false)));
        aCourse("react", PRIVATE,
                aSubcategory("javascript", true,
                        aCategory("front-end", true)));

        aSubcategory("javascript-bootstrap", true,
                aCategory("front-end-1", true));

        aCategory("front-end-2", true);

        List<CategoryProjection> categoryByAmountOfCourse = categoryRepository.findCategoryByAmountOfCourse();

        assertThat(categoryByAmountOfCourse)
                .hasSize(1)
                .extracting(CategoryProjection::getName, CategoryProjection::getNumberOfCourses)
                .containsExactly(
                        tuple("Programação", 4)
                );
    }

    private Category aCategory(String code, boolean active) {
        Category category = new CategoryBuilder()
                .withName("Programação")
                .withCode(code)
                .withDescription("Programe nas principais linguagens e plataformas. Iniciantes são bem vindos nos cursos de lógica e JavaScript.")
                .withOrder(1)
                .withActive(active)
                .withImagePath("https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png")
                .withColorCode("#00c86f")
                .create();
        entityManager.persist(category);
        return category;
    }

    private Subcategory aSubcategory(String code, boolean active, Category category) {
        Subcategory subcategory = new SubcategoryBuilder()
                .withName("Java")
                .withCode(code)
                .withDescription("test repository")
                .withExplanatoryGuide("test repository")
                .withActive(active)
                .withOrder(1)
                .withCategory(category)
                .create();
        entityManager.persist(subcategory);

        return subcategory;
    }

    private Course aCourse(String code, CourseVisibility courseVisibility, Subcategory subcategory) {
        Course course = new CourseBuilder()
                .withName("Java e JDBC: Trabalhando com um banco de dados")
                .withCode(code)
                .withEstimatedTimeToFinish(12)
                .withVisibility(courseVisibility)
                .withTargetAudience("Desenvolvedores que já conheça Orientação a Objetos")
                .withInstructorName("Ana de Jesus")
                .withSummary("Comunique-se com um banco de dados relacional")
                .withLearnedSkills("Evitando SQL Injection ")
                .withSubcategory(subcategory)
                .create();
        entityManager.persist(course);

        return course;
    }

}
