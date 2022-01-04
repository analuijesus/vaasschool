package br.com.vaasschool.test;

import br.com.vaasschool.dao.SubcategoryDao;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class SubcategoryDaoTest {

    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        SubcategoryDao subcategoryDAO = new SubcategoryDao(entityManager);

        try {
            entityManager.getTransaction().begin();

            List<Subcategory> subcategories = subcategoryDAO.searchAllActiveInOrder();
            subcategories.forEach(System.out::println);

            List<String> strings = subcategoryDAO.searchAllEmptyDescriptions();
            strings.forEach(System.out::println);

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
