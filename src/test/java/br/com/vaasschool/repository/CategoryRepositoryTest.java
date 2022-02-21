package br.com.vaasschool.repository;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;
import br.com.vaasschool.model.Subcategory;
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
    public void shouldBringAllWhenTheCategoryIsActiveAndSubcategoryActiveAndPublicCourse() {
        aCourse("java-jdbc", CourseVisibility.PUBLIC,
                aSubcategory("java", true,
                        aCategory("programacao", true)));
        aCourse("python-estatistica", CourseVisibility.PUBLIC,
                aSubcategory("python", true,
                        aCategory("data-science", true)));

        List<Category> categories = categoryRepository.findByActiveCategoryAndActiveSubcategoryAndPublicCourse();

        assertThat(categories)
                .hasSize(2)
                .allMatch(Category::getActive);
    }

    @Test
    public void shouldBringOnlyWhenTheCategoryIsActiveAndSubcategoryIsActiveAndPublicCourse() {
        aCourse("java-jdbc", CourseVisibility.PUBLIC,
                aSubcategory("java", true,
                        aCategory("programacao", true)));
        aCourse("python-estatistica", CourseVisibility.PRIVATE,
                aSubcategory("python", false,
                        aCategory("data-science", false)));

        List<Category> categories = categoryRepository.findByActiveCategoryAndActiveSubcategoryAndPublicCourse();

        assertThat(categories)
                .hasSize(1)
                .allMatch(Category::getActive);
    }

    @Test
    public void shouldIgnoreWhenHavingAPrivateCourseEvenWithTheActiveSubcategoryAndActiveCategory() {
        aCourse("java-jdbc", CourseVisibility.PUBLIC,
                aSubcategory("java", true,
                        aCategory("programacao", true)));
        aCourse("python-estatistica", CourseVisibility.PRIVATE,
                aSubcategory("python", true,
                        aCategory("data-science", true)));

        List<Category> categories = categoryRepository.findByActiveCategoryAndActiveSubcategoryAndPublicCourse();

        assertThat(categories)
                .hasSize(1)
                .allMatch(Category::getActive);
    }

    @Test
    public void shouldIgnoreWhenHavingSubcategoryFalseEvenWithPublicCourseAndActiveCategory() {
        aCourse("python-estatistica", CourseVisibility.PUBLIC,
                aSubcategory("python", false,
                        aCategory("data-science", true)));

        List<Category> categories = categoryRepository.findByActiveCategoryAndActiveSubcategoryAndPublicCourse();

        assertThat(categories)
                .hasSize(0);
    }

    @Test
    public void shouldIgnoreWhenHavingWithACategoryFalseSamePublicCourseAndSubcategoryActive() {
        aCourse("python-estatistica", CourseVisibility.PUBLIC,
                aSubcategory("python", true,
                        aCategory("data-science", false)));

        List<Category> categories = categoryRepository.findByActiveCategoryAndActiveSubcategoryAndPublicCourse();

        assertThat(categories)
                .hasSize(0)
                .allMatch(Category::getActive);
    }

    @Test
    public void shouldIgnoreWhenTheCourseIsPrivateEvenWithAvitvaCategoryAndActiveSubcategory() {
        aCourse("python-estatistica", CourseVisibility.PRIVATE,
                aSubcategory("python", true,
                        aCategory("data-science", true)));

        List<Category> categories = categoryRepository.findByActiveCategoryAndActiveSubcategoryAndPublicCourse();

        assertThat(categories)
                .isEmpty();
    }


//    @Test
//    public void deveModificarAActiveParaFalseQuandoAActiveForTrue() {
//        Category category = aCategory("programacao", true);
//
//        categoryRepository.setActiveFalse(category.getId());
//
//        assertThat(category.getActive())
//                .isFalse();
//    }

//    @Test
//    public void deveManterComoFalseQuandoAActiveForFalso(){
//        Category category = aCategory("programacao", false);
//
//        categoryRepository.setActiveFalse(category.getId());
//        assertThat(category.getId())
//    }

//    testar o método findCategoryByAmountOfCourse(), mas como?

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
