package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.projection.CategoryProjection;
import br.com.vaasschool.projection.CourseProjection;
import br.com.vaasschool.repository.CategoryRepository;
import br.com.vaasschool.repository.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

    AdminController(CategoryRepository categoryRepository, CourseRepository courseRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/admin")
    String showDashboard() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/dashboard")
    String dashboard(Model model) {
        List<CourseProjection> instructorWithGreaterNumberOfCourses = courseRepository.findInstructorWithMoreCourses();
        List<CategoryProjection> allCategoriesByQuantityCourses = categoryRepository.findCategoryByAmountOfCourse();

        model.addAttribute("instructorsWithMoreCourses", instructorWithGreaterNumberOfCourses);
        model.addAttribute("allCategories", allCategoriesByQuantityCourses);
        return "dashboard";
    }

}
