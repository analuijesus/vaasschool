package br.com.vaasschool.repository;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.util.builder.CategoryBuilder;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SubcategoryRepositoryTest {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldModifyActiveToFalseWhenActiveIsTrue() {
        Subcategory subcategory = aSubcategory("java-jdbc", true);

        subcategoryRepository.setActiveFalse(subcategory.getId());
        entityManager.clear();
        Optional<Subcategory> possibleCategory = subcategoryRepository.findById(subcategory.getId());

        assertThat(possibleCategory.get().isActive())
                .isFalse();
    }

    @Test
    public void shouldKeepAsFalseWhenActiveIsFalse(){
        Subcategory subcategory = aSubcategory("java-jdbc", false);

        subcategoryRepository.setActiveFalse(subcategory.getId());
        entityManager.clear();
        Optional<Subcategory> possibleCategory = subcategoryRepository.findById(subcategory.getId());

        assertThat(possibleCategory.get().isActive())
                .isFalse();
    }

    private Subcategory aSubcategory(String code, boolean active) {
        Subcategory subcategory = new SubcategoryBuilder()
                .withName("Java")
                .withCode(code)
                .withDescription("test repository")
                .withExplanatoryGuide("test repository")
                .withActive(active)
                .withOrder(2)
                .withCategory(aCategory())
                .create();
        entityManager.persist(subcategory);

        return subcategory;
    }

    private Category aCategory() {
        Category category = new CategoryBuilder()
                .withName("Programação")
                .withCode("programacao")
                .withDescription("Programe nas principais linguagens e plataformas. Iniciantes são bem vindos nos cursos de lógica e JavaScript.")
                .withOrder(1)
                .withActive(true)
                .withImagePath("https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png")
                .withColorCode("#00c86f")
                .create();
        entityManager.persist(category);
        return category;
    }
}
