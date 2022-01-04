package br.com.vaasschool.dao;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.util.JPAUtil;
import br.com.vaasschool.util.builder.CategoryBuilder;
import br.com.vaasschool.util.builder.CourseBuilder;
import br.com.vaasschool.util.builder.SubcategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseDaoTest {

    private CourseDao courseDao;
    private EntityManager entityManager;
    private Category category;
    private Subcategory subcategory;

    @BeforeEach
    public void setUp() {
        this.entityManager = JPAUtil.getEntityManager();
        this.courseDao = new CourseDao(entityManager);
        entityManager.getTransaction().begin();

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

    @AfterEach
    public void tearDown() {
        entityManager.getTransaction().rollback();
    }

    @Test
    void shouldUpdateAllForPublicVisibility() {
        aCourse(CourseVisibility.PUBLIC, "java-jdbc");
        aCourse(CourseVisibility.PRIVATE, "java-jpa");

        this.courseDao.updateAllForPublicVisibility();
        entityManager.clear();
        List<Course> courses = this.courseDao.findAll();

        assertThat(courses)
                .hasSize(2)
                .allMatch(c -> CourseVisibility.PUBLIC.equals(c.getVisibility()));
    }

    @Test
    void shouldMaintainWithPublicVisibilityPublicCourses() {
        aCourse(CourseVisibility.PUBLIC, "java-jdbc");
        aCourse(CourseVisibility.PUBLIC, "java-jpa");

        this.courseDao.updateAllForPublicVisibility();
        entityManager.clear();
        List<Course> courses = this.courseDao.findAll();

        assertThat(courses)
                .hasSize(2)
                .allMatch(c -> CourseVisibility.PUBLIC.equals(c.getVisibility()));
    }

    @Test
    void shouldBringOnlyCoursesWithPublicVisibility() {
        aCourse(CourseVisibility.PUBLIC, "java-jdbc");
        aCourse(CourseVisibility.PRIVATE, "java-jpa");

        List<Course> courses = this.courseDao.searchAllPublic();

        assertThat(courses)
                .hasSize(1)
                .allMatch(c -> CourseVisibility.PUBLIC.equals(c.getVisibility()));
    }

    @Test
    void shouldBringEmptyWhenNoCoursesWithPublicVisibility(){
        aCourse(CourseVisibility.PRIVATE, "java-jdbc");
        aCourse(CourseVisibility.PRIVATE, "java-jpa");

        List<Course> courses = this.courseDao.searchAllPublic();

        assertThat(courses.isEmpty());
    }

    @Test
    void shouldDeleteCourseByCode() {
        aCourse(CourseVisibility.PUBLIC, "java-jdbc");

        this.courseDao.delete("java-jdbc");
        List<Course> courses = this.courseDao.findAll();

        Assertions.assertTrue(courses.isEmpty());
    }

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
