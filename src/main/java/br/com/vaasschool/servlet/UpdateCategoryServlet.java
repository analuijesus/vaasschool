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

@WebServlet("/alteraCategoria")
public class UpdateCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager entityManager = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(entityManager);

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            Integer order = Integer.parseInt(request.getParameter("order"));
            String description = request.getParameter("description");
            Boolean active = Boolean.parseBoolean(request.getParameter("active"));
            String imagePath = request.getParameter("imagePath");
            String colorCode = request.getParameter("colorCode");

            entityManager.getTransaction().begin();

            Category category = new Category(id, name, code, description, order, active, imagePath, colorCode);
            categoryDao.findById(id);

            categoryDao.save(category);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
        response.sendRedirect("listaCategorias");
    }
}
