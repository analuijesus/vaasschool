package br.com.vaasschool.repository;

import br.com.vaasschool.controller.model.Category;
import br.com.vaasschool.controller.model.Course;
import br.com.vaasschool.controller.model.CourseVisibility;
import br.com.vaasschool.controller.model.Subcategory;
import br.com.vaasschool.projection.CourseProjection;
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

import java.util.Optional;

import static br.com.vaasschool.controller.model.CourseVisibility.PRIVATE;
import static br.com.vaasschool.controller.model.CourseVisibility.PUBLIC;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Subcategory subcategory;

    @Test
    public void ShouldBringTheInstructorWithTheHighestNumberOfCourses() {
        aCourse("java-jdbc", PUBLIC,"Ana de Jesus",
                aSubcategory("java", true,
                        aCategory("programacao", true)));
        aCourse("python-estatistica", PUBLIC, "Madu",
                aSubcategory("python", true,
                        aCategory("data-science", true)));
        aCourse("java-mobile", PUBLIC, "Thais",
                aSubcategory("dart", true,
                        aCategory("mobile", false)));
        aCourse("react", PRIVATE, "Ana de Jesus",
                aSubcategory("javascript", true,
                        aCategory("front-end", true)));
        aCourse("react-native", PRIVATE, "Ana de Jesus",
                aSubcategory("javascript-react", true,
                        aCategory("front-end-1", true)));
        aCourse("bootstrap", PRIVATE,"Madu",
                aSubcategory("bootstrap-2", true,
                        aCategory("front-end-2", true)));

        Optional<CourseProjection> instructorWithMoreCourses = courseRepository.findInstructorWithMoreCourses();

        assertThat(instructorWithMoreCourses).get()
                .extracting(CourseProjection::getInstructorName)
                .isEqualTo("Ana de Jesus");
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

    private Course aCourse(String code, CourseVisibility courseVisibility, String instructorName, Subcategory subcategory) {
        Course course = new CourseBuilder()
                .withName("Java e JDBC: Trabalhando com um banco de dados")
                .withCode(code)
                .withEstimatedTimeToFinish(12)
                .withVisibility(courseVisibility)
                .withTargetAudience("Desenvolvedores que já conheça Orientação a Objetos")
                .withInstructorName(instructorName)
                .withSummary("Comunique-se com um banco de dados relacional")
                .withLearnedSkills("Evitando SQL Injection ")
                .withSubcategory(subcategory)
                .create();
        entityManager.persist(course);

        return course;
    }
}
