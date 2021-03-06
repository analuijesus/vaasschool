package br.com.vaasschool.controller;

import br.com.vaasschool.controller.dto.CourseDto;
import br.com.vaasschool.controller.form.CourseForm;
import br.com.vaasschool.controller.form.validator.CourseFormValidator;
import br.com.vaasschool.controller.model.Category;
import br.com.vaasschool.controller.model.Course;
import br.com.vaasschool.controller.model.CourseVisibility;
import br.com.vaasschool.controller.model.Subcategory;
import br.com.vaasschool.repository.CategoryRepository;
import br.com.vaasschool.repository.CourseRepository;
import br.com.vaasschool.repository.SubcategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@Controller
public class CourseController {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final CourseRepository courseRepository;
    private final CourseFormValidator courseFormValidator;

    @InitBinder("courseForm")
    void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(courseFormValidator);
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}")
    public String listCourses(@PathVariable("categoryCode") String categoryCode,
                              @PathVariable("subcategoryCode") String subcategoryCode,
                              @PageableDefault(size = 5) Pageable pageable, Model model) {
        Subcategory subcategory = subcategoryRepository.findByCode(subcategoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subcategory %s not found", subcategoryCode)));

        Page<Course> coursesPageable = courseRepository.findAllBySubcategory(subcategory, pageable);

        List<CourseDto> courses = coursesPageable.getContent().stream().map(CourseDto::new).toList();

        model.addAttribute("categoryCode", categoryCode);
        model.addAttribute("subcategory", subcategory);
        model.addAttribute("courses", courses);
        model.addAttribute("totalPages", coursesPageable.getTotalPages());

        return "course/listCourse";
    }

    @GetMapping("/admin/courses/new")
    public String showNew(CourseForm courseForm, Model model) {
        List<Subcategory> subcategories = subcategoryRepository.findAllByOrderByName();

        model.addAttribute("visibility", CourseVisibility.values());
        model.addAttribute("subcategories", subcategories);
        return "course/formCourse";
    }

    @PostMapping("/admin/courses/new")
    public String registerNew(@Valid CourseForm courseForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showNew(courseForm, model);
        }
        Subcategory subcategory = subcategoryRepository.findById(courseForm.getSubcategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subcategory %s not found", courseForm.getSubcategoryId())));

        Course course = courseForm.toModel(subcategory);
        courseRepository.save(course);

        return "redirect:/admin/courses/" + subcategory.getCategoryCode() + "/" + course.getSubcategoryCode();
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}/{courseCode}")
    public String showUpdate(@PathVariable("categoryCode") String categoryCode,
                             @PathVariable("subcategoryCode") String subcategoryCode,
                             @PathVariable("courseCode") String courseCode, CourseForm courseForm, BindingResult bindingResult, Model model) {
        List<Subcategory> subcategories = subcategoryRepository.findAll();

        Category category = categoryRepository.findByCode(categoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category %s not found", categoryCode)));

        Subcategory subcategory = subcategoryRepository.findByCode(subcategoryCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subcategory %s not found", subcategoryCode)));

        Course course = courseRepository.findByCode(courseCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course %s not found", courseCode)));

        model.addAttribute("visibility", CourseVisibility.values());
        model.addAttribute("category", category);
        model.addAttribute("subcategory", subcategory);
        model.addAttribute("subcategories", subcategories);
        model.addAttribute("courseForm", bindingResult.hasErrors() ? courseForm : new CourseForm(course));
        return "course/formCourse";
    }

    @PostMapping("/admin/courses/{categoryCode}/{subcategoryCode}/{courseCode}")
    @Transactional
    public String update(@PathVariable("categoryCode") String categoryCode,
                         @PathVariable("subcategoryCode") String subcategoryCode,
                         @PathVariable("courseCode") String courseCode,
                         @Valid CourseForm courseForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showUpdate(categoryCode, subcategoryCode, courseCode, courseForm, bindingResult, model);
        }

        Subcategory subcategory = subcategoryRepository.findById(courseForm.getSubcategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Subcategory %s not found", subcategoryCode)));

        Course course = courseRepository.findById(courseForm.getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, String.format("Course %s not found", courseCode)));

        course.update(courseForm, subcategory);

        return "redirect:/admin/courses/" + categoryCode + "/" + subcategoryCode;
    }
}
