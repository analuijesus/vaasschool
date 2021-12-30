package br.com.vaasschool.test;

import br.com.vaasschool.dao.CategoryDao;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDaoTest {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        CategoryDao categoryDAO = new CategoryDao(entityManager);

        try {
            entityManager.getTransaction().begin();

            List<Category> categories = categoryDAO.searchAllActiveInOrder();
            categories.forEach(System.out::println);

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
