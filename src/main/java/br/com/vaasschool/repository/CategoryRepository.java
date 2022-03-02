package br.com.vaasschool.repository;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.projection.CategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByActiveIsTrue();

    Optional<Category> findByCode(String code);

    List<Category> findAllByOrderByName();

    @Deprecated
    boolean existsByCodeAndIdNot(String code, Long id);

    default boolean existsByCodeWithDifferentId(String code, Long id){
        return existsByCodeAndIdNot(code, id);
    }

    @Query(value = """
            select category.name, count(course.id) as numberOfCourses 
            from Category category 
            join Subcategory subcategory on category.id = subcategory.category_id 
            join Course course on subcategory.id = course.subcategory_id 
            group by category.name 
            order by count(course.id) desc;
            """, nativeQuery = true)
    List<CategoryProjection> findCategoryByAmountOfCourse();

    @Modifying
    @Query("update Category category set category.active = false where category.id = :id")
    void setActiveFalse(@Param("id") Long id);

    @Query(value = """
            select distinct *
            from Category category 
            join Subcategory subcategory on category.id = subcategory.category_id 
            join Course course on subcategory.id = course.subcategory_id
            where category.active = true
            and subcategory.active = true
            and course.visibility = 'PUBLIC'   
            group by category.id         
            order by category.order_visualization
            """, nativeQuery = true)
    List<Category> findByActiveCategoryAndActiveSubcategoryAndPublicCourse();

    boolean existsByCode(String code);
}
