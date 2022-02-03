package br.com.vaasschool.controller;

import br.com.vaasschool.controller.dto.CourseDto;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.repository.CourseRepository;
import br.com.vaasschool.repository.SubcategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class CourseController {

    private final CourseRepository courseRepository;
    private final SubcategoryRepository subcategoryRepository;

    public CourseController(CourseRepository courseRepository, SubcategoryRepository subcategoryRepository) {
        this.courseRepository = courseRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @GetMapping("/admin/courses/{categoryCode}/{subcategoryCode}")
    public String getAll(@PathVariable String subcategoryCode, @PathVariable String categoryCode,
                         @PageableDefault(size = 5) Pageable pageable, Model model){

        Subcategory subcategory = subcategoryRepository.findByCode(subcategoryCode)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Page<Course> coursesPageable = courseRepository.findAllBySubcategory(subcategory, pageable);

        List<CourseDto> courses = coursesPageable.getContent().stream().map(CourseDto::new).toList();

        model.addAttribute("subcategory", subcategory);
        model.addAttribute("categoryCode", categoryCode);
        model.addAttribute("courses", courses);
        model.addAttribute("totalPages", coursesPageable.getTotalPages());

        return "/course/listCourse";
    }

}
