package br.com.vaasschool.repository;

import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.projection.CourseProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findAllBySubcategory(Subcategory subcategory, Pageable pageable);

    @Query(value = """
            select instructor_name as instructorName, count(*) as numberOfCourses
            from Course 
            group by instructorName 
            order by numberOfCourses desc limit 1 
            """, nativeQuery = true)
    Optional<CourseProjection> findInstructorWithMoreCourses();

    Optional<Course> findByCode(String courseCode);
}
