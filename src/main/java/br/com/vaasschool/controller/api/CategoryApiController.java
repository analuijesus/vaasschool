package br.com.vaasschool.controller.api;

import br.com.vaasschool.controller.api.dto.CategoryApiDto;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.repository.CategoryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryApiController {

    private final CategoryRepository categoryRepository;

    public CategoryApiController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(path = "/api/categories", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
    @Cacheable(value = "listOfCategories")
    public ResponseEntity<List<CategoryApiDto>> listActiveCategories() {
        List<Category> categories = categoryRepository.findAllByActiveIsTrue();
        List<CategoryApiDto> categoryApiDtos = categories.stream().map(CategoryApiDto::new).toList();

        return ResponseEntity.ok().body(categoryApiDtos);
    }

    @GetMapping(value = "api/categories/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
    @CacheEvict(value = "listOfCategories", allEntries = true)
    public String clearCache() {
        return "Cache limpo com sucesso!";
    }
}