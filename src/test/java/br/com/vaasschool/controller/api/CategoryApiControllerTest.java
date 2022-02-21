package br.com.vaasschool.controller.api;

import br.com.vaasschool.controller.api.dto.CategoryApiDto;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.repository.CategoryRepository;
import br.com.vaasschool.repository.CourseRepository;
import br.com.vaasschool.repository.SubcategoryRepository;
import br.com.vaasschool.util.builder.CategoryBuilder;
import br.com.vaasschool.util.builder.CourseBuilder;
import br.com.vaasschool.util.builder.SubcategoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

import static br.com.vaasschool.model.CourseVisibility.PUBLIC;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @AfterEach
    public void tearDown(){
        categoryRepository.deleteAll();
        subcategoryRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    @Transactional
    public void shouldReturnSuccessContentTypeTextJsonValue() throws Exception {
        URI uri = new URI("/api/categories");

        Category category = aCategory("programacao", true);

        Course course = aCourse("java-jdbc", PUBLIC,
                aSubcategory("java", true, category));
        CategoryApiDto categoryApiDto = new CategoryApiDto(category);

        List<CategoryApiDto> list = List.of(categoryApiDto);
        String json = new ObjectMapper().writeValueAsString(list);

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200))
                .andExpect(MockMvcResultMatchers.content().string(json));
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

        categoryRepository.save(category);

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

        subcategoryRepository.save(subcategory);

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

        courseRepository.save(course);

        return course;
    }
}
