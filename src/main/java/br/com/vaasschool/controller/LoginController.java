package br.com.vaasschool.controller;

import br.com.vaasschool.controller.dto.CategoryLoginDto;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    private final CategoryRepository categoryRepository;

    public LoginController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        List<Category> categories = categoryRepository.findByActiveCategoryActiveSubcategoryAndPublicCourse();
        List<CategoryLoginDto> categoryWithSubcategories = categories.stream().map(CategoryLoginDto::new).toList();

        model.addAttribute("categories", categoryWithSubcategories);

        return "login";
    }
}
