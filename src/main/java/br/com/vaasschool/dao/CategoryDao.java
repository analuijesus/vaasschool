package br.com.vaasschool.dao;

import br.com.vaasschool.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CategoryDao {

    private EntityManager entityManager;

    public CategoryDao(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List<Category> searchAllActiveInOrder() {

        Query jpqCategoryActive = entityManager.createQuery("""
                select category 
                from Category category 
                where category.active = true
                order by category.order asc 
                """);
        return jpqCategoryActive.getResultList();
    }

    public List<Category> findAll() {
        return entityManager.createQuery("select category from Category category", Category.class).getResultList();
    }
}
