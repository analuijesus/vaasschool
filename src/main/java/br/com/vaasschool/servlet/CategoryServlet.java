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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listaCategorias")
public class CategoryServlet extends HttpServlet {

    EntityManager entityManager = JPAUtil.getEntityManager();
    CategoryDao categoryDao = new CategoryDao(entityManager);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> categories = categoryDao.findAll();

        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html>");
        printWriter.println("<head>");
        printWriter.println("<meta charset=\"UTF-8\">");
        printWriter.println("</head>");

        printWriter.println("<body>");
        printWriter.println("<table>");
        printWriter.println("<thead>");
        printWriter.println("<tr>");

        printWriter.print("<th>Nome</th>");
        printWriter.print("<th>Código</th>");
        printWriter.print("<th>Ordem</th>");
        printWriter.print("<th>Descrição</th>");
        printWriter.print("<th>Status</th>");
        printWriter.print("<th>Ícone</th>");
        printWriter.print("<th>Cor</th>");

        printWriter.println("</tr>");
        printWriter.println("</thead>");
        printWriter.println("<tbody>");

        for (Category c : categories) {
            printWriter.println("<tr>");

            printWriter.println("<td>" + c.getName() + "</td>");
            printWriter.println("<td>" + c.getCode() + "</td>");
            printWriter.println("<td>" + c.getOrder() + "</td>");
            printWriter.println("<td>" + c.getDescription() + "</td>");
            printWriter.println("<td>" + c.getActive() + "</td>");
            printWriter.println("<td><img src=\"" + c.getImagePath() + "\"></td>");
            printWriter.println("<td>" + c.getColorCode() + "</td>");

            printWriter.println("</tr>");
        }

        printWriter.println("</tbody>");
        printWriter.println("</table>");
        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}
