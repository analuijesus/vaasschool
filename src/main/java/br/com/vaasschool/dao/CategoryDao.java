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

    public void save(Category category) {
        if (category.getId() == null) {
            this.entityManager.persist(category);
            System.out.println("Curso Id " + category.getId() + " inserido com sucesso!");
        } else {
            this.entityManager.merge(category);
            System.out.println("Curso Id " + category.getId() + " alterado com sucesso!");
        }
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

    public Category findById(Long id) {
        Category category = entityManager.createQuery("select category from Category category where category.id = :id", Category.class)
                .setParameter("id", id)
                .getSingleResult();
        return category;
    }

    public Category disableCategory(Long id){
        Category category = findById(id);
        category.setActive(false);
        return category;
    }
}
