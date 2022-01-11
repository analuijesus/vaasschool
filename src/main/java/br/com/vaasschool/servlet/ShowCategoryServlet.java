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

@WebServlet("/mostraCategoria")
public class ShowCategoryServlet  extends HttpServlet {

    EntityManager entityManager = JPAUtil.getEntityManager();
    CategoryDao categoryDao = new CategoryDao(entityManager);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        entityManager.getTransaction().begin();
        Category category = categoryDao.findById(id);

        entityManager.getTransaction().commit();
        entityManager.close();

        request.setAttribute("category", category);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/formUpdateCategory.jsp");
        requestDispatcher.forward(request, response);
    }
}
