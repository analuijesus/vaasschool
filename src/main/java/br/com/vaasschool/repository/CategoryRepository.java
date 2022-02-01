package br.com.vaasschool.repository;

import br.com.vaasschool.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByActiveIsTrue();

    Optional<Category> findByCode(String code);
}
