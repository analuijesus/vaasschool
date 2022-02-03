package br.com.vaasschool.repository;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.projection.CategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByActiveIsTrue();

    Optional<Category> findByCode(String code);

    List<Category> findAllByOrderByName();

    @Query(value = """
            select category.name, count(course.id) as numberOfCourses 
            from category 
            left join subcategory subcategory on category.id = subcategory.category_id 
            left join course course on subcategory.id = course.subcategory_id 
            group by category.name 
            order by count(course.id) desc;
            """, nativeQuery = true)
    List<CategoryProjection> findCategoryByAmountOfCourse();
}
