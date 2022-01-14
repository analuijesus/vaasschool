package br.com.vaasschool.servlet;

import br.com.vaasschool.dao.CategoryDao;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/desativaCategoria")
public class DisableCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager entityManager = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(entityManager);

        try {
            entityManager.getTransaction().begin();
            Long id = Long.valueOf(request.getParameter("id"));

            Category category = categoryDao.findById(id);
            category.deactivate();

            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();

        } finally {
            entityManager.close();
        }
    }
}
