package br.com.vaasschool.controller;

import br.com.vaasschool.controller.dto.CategoryDto;
import br.com.vaasschool.controller.dto.CategoryPageDto;
import br.com.vaasschool.controller.form.CategoryForm;
import br.com.vaasschool.controller.form.validator.CategoryFormValidator;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryFormValidator categoryFormValidator;

    public CategoryController(CategoryRepository categoryRepository, CategoryFormValidator categoryFormValidator) {
        this.categoryRepository = categoryRepository;
        this.categoryFormValidator = categoryFormValidator;
    }

    @InitBinder("categoryForm")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(categoryFormValidator);
    }

    @GetMapping("/admin/categories")
    public String listCategories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(CategoryDto::new).toList();
        model.addAttribute("categories", categoryDtos);
        return "category/listCategory";
    }

    @GetMapping("/admin/categories/new")
    public String showNew(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        return "category/formCategory";
    }

    @PostMapping("/admin/categories/new")
    public String registerNew(@Valid CategoryForm categoryForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/formCategory";
        }
        categoryRepository.save(categoryForm.toModel());
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/{code}")
    public String showUpdate(@PathVariable("code") String code, Model model, CategoryForm courseForm, BindingResult bindingResult) {
        Category category = categoryRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, String.format("Category %s not found", code)));

        model.addAttribute("categoryForm", bindingResult.hasErrors() ? courseForm : new CategoryForm(category));

        return "category/formCategory";
    }

    @PostMapping("/admin/categories/{code}")
    @Transactional
    public String update(@PathVariable("code") String code, @Valid CategoryForm categoryForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showUpdate(code, model, categoryForm, bindingResult);
        }

        Category category = categoryRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, String.format("Category %s not found", code)));

        category.update(categoryForm);

        return "redirect:/admin/categories";
    }

    @GetMapping("/category/{categoryCode}")
    public String publicPage(@PathVariable("categoryCode") String code, Model model) {

        Category category = categoryRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, String.format("Category %s not found", code)));

        model.addAttribute("category", new CategoryPageDto(category));

        return "category/pageCategory";
    }

    @GetMapping("/")
    public String publicPageRedirect() {
        return "redirect:/category/programacao";
    }

    @PostMapping("/admin/deactivateCategory")
    @Transactional
    @ResponseBody
    public void disableStatus(Long id, Model model) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, String.format("Category %s not found", id)));

        categoryRepository.setActiveFalse(id);

        model.addAttribute("category", category);
    }
}