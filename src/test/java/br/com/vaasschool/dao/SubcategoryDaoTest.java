package br.com.vaasschool.dao;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.util.JPAUtil;
import br.com.vaasschool.util.builder.CategoryBuilder;
import br.com.vaasschool.util.builder.SubcategoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubcategoryDaoTest {

    private SubcategoryDao subcategoryDao;
    private EntityManager entityManager;
    private Category category;

    @BeforeEach
    public void setUp() {
        this.entityManager = JPAUtil.getEntityManager();
        this.subcategoryDao = new SubcategoryDao(entityManager);
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
    }

    @AfterEach
    public void tearDown() {
        entityManager.getTransaction().rollback();
    }

    @Test
    void shouldBringTheSubcategoriesInAscendingOrder() {
        aSubcategory("programacao-java-jpa", true, 2, "Aprenda a utilizar a JPA com o " +
                "Hibernate para persistência em Java, seguindo o modelo de ORM.");
        aSubcategory("programacao-java-jdbc", true, 1, "JDBC é uma API, com um conjunto" +
                " de interfaces que podem ser implementadas para a conexão a diversos bancos de dados.");

        List<Subcategory> subcategories = this.subcategoryDao.searchAllActiveInOrder();

        Integer order = subcategories.get(0).getOrder();

        assertThat(subcategories)
                .hasSize(2)
                .allMatch(c -> order.equals(1));
    }

    @Test
    void shouldBringAllSubcategoriesActive() {
        aSubcategory("programacao-java-jpa", true, 1, "Aprenda a utilizar a JPA com o " +
                "Hibernate para persistência em Java, seguindo o modelo de ORM.\n");
        aSubcategory("programacao-java-jdbc", true, 2, "JDBC é uma API, com um conjunto " +
                "de interfaces que podem ser implementadas para a conexão a diversos bancos de dados.");

        List<Subcategory> subcategories = this.subcategoryDao.searchAllActiveInOrder();

        assertThat(subcategories)
                .hasSize(2)
                .allMatch(c -> c.getActive().equals(true));
    }

    @Test
    void shouldBringOnlyActiveSubcategories() {
        aSubcategory("programacao-java-jpa", true, 1, "Aprenda a utilizar a JPA com o " +
                "Hibernate para persistência em Java, seguindo o modelo de ORM.");
        aSubcategory("programacao-java-jdbc", false, 2, "JDBC é uma API, com um conjunto" +
                "de interfaces que podem ser implementadas para a conexão a diversos bancos de dados.");

        List<Subcategory> subcategories = this.subcategoryDao.searchAllActiveInOrder();

        assertThat(subcategories)
                .hasSize(1)
                .allMatch(c -> c.getActive().equals(true));
    }

    @Test
    void shouldReturnEmptyWhenNoSubcategoriesAreActive() {
        aSubcategory("programacao-java-jpa", false, 1, "Aprenda a utilizar a JPA com o " +
                "Hibernate para persistência em Java, seguindo o modelo de ORM.");
        aSubcategory("programacao-java-jdbc", false, 2, "JDBC é uma API, com um conjunto" +
                "de interfaces que podem ser implementadas para a conexão a diversos bancos de dados.");

        List<Subcategory> subcategories = this.subcategoryDao.searchAllActiveInOrder();

        assertThat(subcategories.isEmpty());
    }

    @Test
    void shouldSearchAllSubcategoriesWithEmptyDescription() {
        aSubcategory("programacao-java-jpa", true, 1, "");
        aSubcategory("programacao-java-jdbc", true, 2, "");

        List<Subcategory> subcategories = this.subcategoryDao.searchAllEmptyDescriptions();

        assertThat(subcategories)
                .hasSize(2)
                .allMatch(c -> c.getDescription().isEmpty());
    }

    @Test
    void shouldDisplayOnlySubcategoriesWithEmptyDescription() {
        aSubcategory("programacao-java-jpa", true, 1, "Aprenda a utilizar a JPA com o " +
                "Hibernate para persistência em Java, seguindo o modelo de ORM.");
        aSubcategory("programacao-java-jdbc", true, 2, "");

        List<Subcategory> subcategories = this.subcategoryDao.searchAllEmptyDescriptions();

        String description = subcategories.get(0).getDescription();

        assertThat(subcategories)
                .hasSize(1)
                .allMatch(c -> description.equals(c.getDescription()));
    }

    @Test
    void shouldReturnEmptyWhenSubcategoryHaveDescription() {
        aSubcategory("programacao-java-jpa", true, 1, "Aprenda a utilizar a JPA com o " +
                "Hibernate para persistência em Java, seguindo o modelo de ORM.");
        aSubcategory("programacao-java-jdbc", true, 2, "JDBC é uma API, com um conjunto" +
                "de interfaces que podem ser implementadas para a conexão a diversos bancos de dados.");

        List<Subcategory> subcategories = this.subcategoryDao.searchAllEmptyDescriptions();

        assertThat(subcategories.isEmpty());
    }

    private Subcategory aSubcategory(String code, boolean active, Integer order, String description) {
        Subcategory subcategory = new SubcategoryBuilder()
                .withName("Java")
                .withCode(code)
                .withDescription(description)
                .withActive(active)
                .withOrder(order)
                .withCategory(category)
                .create();
        entityManager.persist(subcategory);

        return subcategory;
    }
}
