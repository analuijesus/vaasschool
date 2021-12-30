package br.com.vaasschool.dao;

import br.com.vaasschool.model.Subcategory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class SubcategoryDao {

    private EntityManager entityManager;

    public SubcategoryDao(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<Subcategory> searchAllActiveInOrder() {

        Query jpqlSubcategoryActive = entityManager.createQuery("""
                select subcategory
                from Subcategory subcategory 
                where subcategory.active = true   
                order by subcategory.order asc
                """);
        return jpqlSubcategoryActive.getResultList();
    }

    public List<Subcategory> searchAllEmptyDescriptions() {
        Query jpqlDescriptionEmpty = entityManager.createQuery("""
                select subcategory
                from Subcategory subcategory 
                where subcategory.description = ''   
                """);

        return jpqlDescriptionEmpty.getResultList();
    }

    public List<Subcategory> findAll() {
        return entityManager.createQuery("select subcategory from Subcategory subcategory", Subcategory.class)
                .getResultList();
    }
}
