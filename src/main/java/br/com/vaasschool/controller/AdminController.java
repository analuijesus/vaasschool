package br.com.vaasschool.controller;

import br.com.vaasschool.projection.CategoryProjection;
import br.com.vaasschool.projection.CourseProjection;
import br.com.vaasschool.repository.CategoryRepository;
import br.com.vaasschool.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@Controller
public class AdminController {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

    @GetMapping("/admin")
    String showDashboard() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    String dashboard(Model model) {
        CourseProjection instructorWithGreaterNumberOfCourses = courseRepository.findInstructorWithMoreCourses()
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        List<CategoryProjection> allCategoriesByQuantityCourses = categoryRepository.findCategoryByAmountOfCourse();

        model.addAttribute("instructorsWithMoreCourses", instructorWithGreaterNumberOfCourses);
        model.addAttribute("allCategories", allCategoriesByQuantityCourses);
        return "dashboard";
    }
}
