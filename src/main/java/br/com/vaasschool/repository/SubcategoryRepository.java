package br.com.vaasschool.repository;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    Optional<Subcategory> findByCode(String code);

    List<Subcategory> findAllByCategoryOrderByOrder(Category category);

    List<Subcategory> findAllByOrderByName();
}
