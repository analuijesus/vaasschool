package br.com.vaasschool.servlet;

import br.com.vaasschool.dao.CategoryDao;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cadastraCategoria")
public class CreateCategoryServlet extends HttpServlet {

    EntityManager entityManager = JPAUtil.getEntityManager();
    CategoryDao categoryDao = new CategoryDao(entityManager);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String code = request.getParameter("code");
        Integer order = Integer.parseInt(request.getParameter("order"));
        String description = request.getParameter("description");
        Boolean active = Boolean.parseBoolean(request.getParameter("active"));
        String imagePath = request.getParameter("imagePath");
        String colorCode = request.getParameter("colorCode");

        entityManager.getTransaction().begin();
        Category category = new Category(name, code, description, order, active, imagePath, colorCode);

        categoryDao.save(category);

        entityManager.getTransaction().commit();
        entityManager.close();

        response.sendRedirect("listaCategorias");
    }
}
