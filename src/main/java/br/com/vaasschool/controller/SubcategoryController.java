package br.com.vaasschool.controller;

import br.com.vaasschool.controller.dto.SubcategoryDto;
import br.com.vaasschool.controller.form.SubcategoryForm;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.repository.CategoryRepository;
import br.com.vaasschool.repository.SubcategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class SubcategoryController {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryController(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @GetMapping("/admin/subcategories/{categoryCode}")
    public String listSubcategories(@PathVariable String categoryCode, Model model) {
        Category category = categoryRepository.findByCode(categoryCode)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, categoryCode));
        List<Subcategory> subcategories = subcategoryRepository.findAllByOrderByOrder();
        List<SubcategoryDto> subCategoriesDtos = subcategories.stream().map(SubcategoryDto::new).toList();

        model.addAttribute("subcategories", subCategoriesDtos);
        model.addAttribute("categoryName", category.getName());
        model.addAttribute("categoryCode", categoryCode);

        return "subcategory/listSubcategory";
    }

    @GetMapping("/admin/subcategories/new")
    public String showNew(SubcategoryForm subcategoryForm, Model model) {

        List<Category> categories = categoryRepository.findAllByOrderByName();
        model.addAttribute("categories", categories);
        model.addAttribute("subcategoryForm", subcategoryForm == null ? new SubcategoryForm() : subcategoryForm);
        return "subcategory/formSubcategory";
    }

    @PostMapping("/admin/subcategories/new")
    public String registerNew(@Valid SubcategoryForm subcategoryForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showNew(subcategoryForm, model);
        }
        Category category = categoryRepository.findById(subcategoryForm.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category %s not found", subcategoryForm.getCategoryId())));

        Subcategory subcategory = subcategoryForm.toModel(category);
        subcategoryRepository.save(subcategory);

        return "redirect:/admin/subcategories/" + subcategory.getCategoryCode();
    }

    @GetMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    public String showUpdate(@PathVariable("categoryCode") String categoryCode, @PathVariable("subcategoryCode")
            String subcategoryCode, Model model) {
        List<Category> categories = categoryRepository.findAll();

        Subcategory subcategory = subcategoryRepository.findByCode(subcategoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subcategory %s not found", subcategoryCode)));

        Category category = categoryRepository.findByCode(categoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category %s not found", categoryCode)));

        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        model.addAttribute("subcategoryForm", new SubcategoryDto(subcategory));
        return "subcategory/formSubcategory";
    }

    @PostMapping("/admin/subcategories/{categoryCode}/{subcategoryCode}")
    @Transactional
    public String update(@PathVariable("categoryCode") String categoryCode,
                         @PathVariable("subcategoryCode") String subcategoryCode,
                         @Valid SubcategoryForm subcategoryForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showUpdate(categoryCode, subcategoryCode, model);
        }
        Category category = categoryRepository.findById(subcategoryForm.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category %s not found", categoryCode)));

        Subcategory subcategory = subcategoryForm.convert(subcategoryRepository);

        return "redirect:/admin/subcategories/" + categoryCode;
    }

    @PostMapping("/admin/deactivateSubcategory")
    @Transactional
    @ResponseBody
    public void disableStatus(Long id, Model model) {
        Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, String.format("Subcategory %s not found", id)));

        subcategoryRepository.setActiveFalse(id);

        model.addAttribute("subcategory", subcategory);
    }
}
