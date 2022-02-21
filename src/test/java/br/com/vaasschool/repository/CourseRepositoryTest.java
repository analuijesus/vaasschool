package br.com.vaasschool.repository;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.util.builder.CategoryBuilder;
import br.com.vaasschool.util.builder.CourseBuilder;
import br.com.vaasschool.util.builder.SubcategoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    final private TestEntityManager entityManager;

    private Category category;
    private Subcategory subcategory;

    public CourseRepositoryTest(CourseRepository courseRepository, TestEntityManager entityManager) {
        this.courseRepository = courseRepository;
        this.entityManager = entityManager;
    }

    @BeforeEach
    public void setUp() {
    this.category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withDescription("Programe nas principais linguagens e plataformas. Iniciantes são bem vindos nos cursos de lógica e JavaScript.")
                .withOrder(1)
                .withActive(true)
                .withImagePath("https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png")
                .withColorCode("#00c86f")
                .create();
        entityManager.persist(category);

        this.subcategory = new SubcategoryBuilder()
                .withName("Java e Persistência")
                .withCode("java-e-persistencia")
                .withDescription("")
                .withOrder(2)
                .withCategory(category)
                .create();

        entityManager.persist(subcategory);
}

//    findInstructorWithMoreCourses como testar?


    private Course aCourse(CourseVisibility courseVisibility, String code) {
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
