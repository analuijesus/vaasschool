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

    EntityManager entityManager = JPAUtil.getEntityManager();
    CategoryDao categoryDao = new CategoryDao(entityManager);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        Integer order = Integer.parseInt(request.getParameter("order"));
        String description = request.getParameter("description");
        Boolean active = Boolean.parseBoolean(request.getParameter("active"));
        String imagePath = request.getParameter("imagePath");
        String colorCode = request.getParameter("colorCode");

        entityManager.getTransaction().begin();
        Category category = categoryDao.findById(id);

        category.setId(id);
        category.setName(name);
        category.setCode(code);
        category.setOrder(order);
        category.setDescription(description);
        category.setActive(active);
        category.setImagePath(imagePath);
        category.setColorCode(colorCode);

        categoryDao.save(category);
        entityManager.getTransaction().commit();
        entityManager.close();

        response.sendRedirect("listaCategorias");
    }
}
