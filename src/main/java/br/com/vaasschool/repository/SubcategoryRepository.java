package br.com.vaasschool.repository;

import br.com.vaasschool.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    Optional<Subcategory> findByCode(String code);

    List<Subcategory> findAllByOrderByOrder();

    @Modifying
    @Query("update Subcategory subcategory set subcategory.active = false where subcategory.id = :id")
    void setActiveFalse (@Param("id") Long id);
}
