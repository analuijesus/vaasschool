package br.com.vaasschool.filegenerator;

import br.com.vaasschool.dao.CourseDAO;
import br.com.vaasschool.dao.factory.ConnectionFactory;
import br.com.vaasschool.dto.CourseDto;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.List;

public class CourseHtmlPageGenerator {

    public static void main(String[] args) throws Exception {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            connection.setAutoCommit(false);

            try {
                CourseDAO courseDao = new CourseDAO(connection);
                List<CourseDto> courses = courseDao.searchAllPublic();

                writePage(courses);
            } catch (Exception ex) {
                connection.rollback();
                throw ex;
            }
        }
    }

    private static void writePage(List<CourseDto> courses) throws Exception {
        try (OutputStream os = new FileOutputStream("relatorio-filtro-cursos.html")) {
            PrintStream ps = new PrintStream(os);

            ps.println("<html>");
            ps.println("<head>");
            ps.println("<meta charset=\"UTF-8\">");
            ps.println("</head>");
            ps.println("<body>");
            ps.println("<table>");

            ps.println("<thead>");
            ps.println("<tr>");

            ps.print("<th>Id Curso</th>");
            ps.print("<th>Nome Curso</th>");
            ps.print("<th>Tempo de Finalização do Curso</th>");
            ps.print("<th>Id Subcategoria</th>");
            ps.print("<th>Nome Subcategoria</th>");
            ps.println("</tr>");
            ps.println("</thead>");
            ps.println("<tbody>");

            for (CourseDto c : courses) {
                ps.println("<tr>");
                ps.println("<td>" + c.getId() + "</td>");
                ps.println("<td>" + c.getName() + "</td>");
                ps.println("<td>" + c.getEstimatedTimeToFinish() + "</td>");
                ps.println("<td>" + c.getSubcategoryDto().getId() + "</td>");
                ps.println("<td>" + c.getSubcategoryDto().getName() + "</td>");
                ps.println("</tr>");
            }

            ps.println("</tbody>");
            ps.println("</table>");
            ps.println("</body>");
            ps.println("</html>");

        }
    }
}
