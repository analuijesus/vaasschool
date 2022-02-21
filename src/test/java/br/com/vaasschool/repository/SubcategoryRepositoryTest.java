package br.com.vaasschool.repository;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.util.builder.CategoryBuilder;
import br.com.vaasschool.util.builder.SubcategoryBuilder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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

    private Category category;


//    @Test
//    public void deveModificarParaFalseQuandoAActiveForTrue() {
//        Subcategory subcategory = aSubcategory("programacao", true);
//
//        subcategoryRepository.setActiveFalse(subcategory.getId());
//        assertThat(subcategory.getId())
//    }
//
//    @Test
//    public void deveManterComoFalseQuandoAActiveForFalso() {
//        Subcategory subcategory = aSubcategory("programacao", false);
//
//        subcategoryRepository.setActiveFalse(subcategory.getId());
//        assertThat(subcategory.getId())
//    }

    private Subcategory aSubcategory(String code, boolean active) {
        Subcategory subcategory = new SubcategoryBuilder()
                .withName("Java")
                .withCode(code)
                .withDescription("test repository")
                .withExplanatoryGuide("test repository")
                .withActive(active)
                .withOrder(2)
                .withCategory(category)
                .create();
        entityManager.persist(subcategory);

        return subcategory;
    }
}
