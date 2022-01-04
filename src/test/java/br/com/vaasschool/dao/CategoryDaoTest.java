package br.com.vaasschool.dao;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.util.JPAUtil;
import br.com.vaasschool.util.builder.CategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryDaoTest {

    private CategoryDao categoryDao;
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        this.entityManager = JPAUtil.getEntityManager();
        this.categoryDao = new CategoryDao(entityManager);
        entityManager.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        entityManager.getTransaction().rollback();
    }

    @Test
    void shouldBringTheCategoriesInAscendingOrder() {
        aCategory("programacao-java-jpa", true, 2);
        aCategory("programacao-java-jdbc", true, 1);

        entityManager.clear();
        List<Category> categories = this.categoryDao.searchAllActiveInOrder();

        assertThat(categories)
                .hasSize(2)
                .extracting(Category::getOrder)
                .containsExactly(1,2);
    }

    @Test
    void shouldBringAllActiveCategories() {
        aCategory("programacao-java-jpa", true, 1);
        aCategory("programacao-java-jdbc", true, 2);

        entityManager.clear();
        List<Category> categories = this.categoryDao.searchAllActiveInOrder();

        assertThat(categories)
                .hasSize(2)
                .allMatch(Category::getActive);
    }

    @Test
    void shouldBringOnlyActiveCategories() {
        aCategory("programacao-java-jpa", true, 1);
        aCategory("programacao-java-jdbc", false, 2);

        entityManager.clear();
        List<Category> categories = this.categoryDao.searchAllActiveInOrder();

        assertThat(categories)
                .hasSize(1)
                .allMatch(Category::getActive);
    }

    @Test
    void shouldReturnEmptyWhenNoCategoriesAreActive() {
        aCategory("programacao-java-jpa", false, 1);
        aCategory("programacao-java-jdbc", false, 2);

        entityManager.clear();
        List<Category> categories = this.categoryDao.searchAllActiveInOrder();

        assertThat(categories.isEmpty());
    }

    private Category aCategory(String code, boolean active, Integer order) {
        Category category = new CategoryBuilder()
                .withName("Programação")
                .withCode(code)
                .withDescription("Programe nas principais linguagens e plataformas. Iniciantes são bem vindos nos cursos de lógica e JavaScript.")
                .withOrder(order)
                .withActive(active)
                .withImagePath("https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png")
                .withColorCode("#00c86f")
                .create();
        entityManager.persist(category);
        return category;
    }
}
