package br.com.vaasschool.filegenerator;

import br.com.vaasschool.dao.CategoryDao;
import br.com.vaasschool.dao.CourseDao;
import br.com.vaasschool.dao.SubcategoryDao;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.util.JPAUtil;

import javax.persistence.EntityManager;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class ReportHtmlPageGenerator {

    public static void main(String[] args) throws Exception {

        EntityManager entityManager = JPAUtil.getEntityManager();
        CategoryDao categoryDao = new CategoryDao(entityManager);
        SubcategoryDao subcategoryDao = new SubcategoryDao(entityManager);
        CourseDao courseDao = new CourseDao(entityManager);

        List<Category> activeCategory = categoryDao.searchAllActiveInOrder();
        List<Subcategory> activeSubcategories = subcategoryDao.searchAllActiveInOrder();
        List<Subcategory> emptyDescription = subcategoryDao.searchAllEmptyDescriptions();
        List<Course> publicCourse = courseDao.searchAllPublic();

        writePage(activeCategory, activeSubcategories, publicCourse, emptyDescription);

    }

    private static void writePage(List<Category> categories, List<Subcategory> subcategories,
                                  List<Course> courses, List<Subcategory> subcategoriesName) throws Exception {
        try (OutputStream os = new FileOutputStream("relatorio-vaasschool.html")) {
            PrintStream ps = new PrintStream(os);

            ps.println("<html>");
            ps.println("<head>");
            ps.println("<meta charset=\"UTF-8\">");
            ps.println("<title>Relatório Vaas School</title>");
            ps.println("</head>");

            ps.println("<body>");
            ps.println("<table>");
            ps.println("<thead>");
            ps.println("<tr>");
            ps.println("<h2>Categoria Ativa</h2>");
            ps.print("<th>Nome Categoria</th>");
            ps.print("<th>Código Categoria</th>");
            ps.print("<th>Ordem Categoria/th>");
            ps.print("<th>Descrição Categoria</th>");
            ps.print("<th>Status Categoria</th>");
            ps.print("<th>Icone Categoria</th>");
            ps.print("<th>Cor Categoria</th>");
            ps.println("</tr>");
            ps.println("</thead>");

            ps.println("<tbody>");

            for (Category category : categories) {
                ps.println("<tr>");
                ps.println("<td>" + category.getName() + "</td>");
                ps.println("<td>" + category.getCode() + "</td>");
                ps.println("<td>" + category.getOrder() + "</td>");
                ps.println("<td>" + category.getDescription() + "</td>");
                ps.println("<td>" + category.getActive() + "</td>");
                ps.println("<td>" + category.getImagePath() + "</td>");
                ps.println("<td>" + category.getColorCode() + "</td>");
                ps.println("</tr>");
            }

            ps.println("</tbody>");
            ps.println("</table>");

            ps.println("<table>");
            ps.println("<thead>");
            ps.println("<tr>");
            ps.println("<h2>Subcategoria Ativa</h2>");
            ps.print("<th>Nome Subcategoria</th>");
            ps.print("<th>Código Subcategoria</th>");
            ps.print("<th>Ordem Subcategoria/th>");
            ps.print("<th>Descrição Subcategoria</th>");
            ps.print("<th>Status Subcategoria</th>");
            ps.print("<th>Categoria Subcategoria</th>");
            ps.println("</tr>");
            ps.println("</thead>");

            ps.println("<tbody>");

            for (Subcategory subcategory : subcategories) {
                ps.println("<tr>");
                ps.println("<td>" + subcategory.getName() + "</td>");
                ps.println("<td>" + subcategory.getCode() + "</td>");
                ps.println("<td>" + subcategory.getOrder() + "</td>");
                ps.println("<td>" + subcategory.getDescription() + "</td>");
                ps.println("<td>" + subcategory.getActive() + "</td>");
                ps.println("<td>" + subcategory.getCategory().getName()+ "</td>");
                ps.println("</tr>");
            }

            ps.println("</tbody>");
            ps.println("</table>");

            ps.println("<table>");
            ps.println("<thead>");
            ps.println("<tr>");
            ps.println("<h2>Cursos Públicos</h2>");
            ps.print("<th>Nome Curso</th>");
            ps.print("<th>Código Curso</th>");
            ps.print("<th>Tempo Finalização do Curso/th>");
            ps.print("<th>Visibilidade Curso</th>");
            ps.print("<th>Publico Alvo</th>");
            ps.print("<th>Nome Instrutor</th>");
            ps.print("<th>Ementa</th>");
            ps.print("<th>Habilidades</th>");
            ps.print("<th>Nome Subcategoria</th>");
            ps.println("</tr>");
            ps.println("</thead>");

            ps.println("<tbody>");

            for (Course course : courses) {
                ps.println("<tr>");
                ps.println("<td>" + course.getName() + "</td>");
                ps.println("<td>" + course.getCode() + "</td>");
                ps.println("<td>" + course.getEstimatedTimeToFinish() + "</td>");
                ps.println("<td>" + course.getVisibility() + "</td>");
                ps.println("<td>" + course.getTargetAudience() + "</td>");
                ps.println("<td>" + course.getInstructorName() + "</td>");
                ps.println("<td>" + course.getSummary() + "</td>");
                ps.println("<td>" + course.getLearnedSkills() + "</td>");
                ps.println("<td>" + course.getSubcategory().getName()+ "</td>");
                ps.println("</tr>");
            }

            ps.println("</tbody>");
            ps.println("</table>");

            ps.println("<table>");
            ps.println("<thead>");
            ps.println("<tr>");
            ps.println("<h2>Subcategorias sem Descrição</h2>");
            ps.print("<th>Nome Subcategoria</th>");

            ps.println("</tr>");
            ps.println("</thead>");

            ps.println("<tbody>");

            for (Subcategory subcategory : subcategoriesName) {
                ps.println("<tr>");
                ps.println("<td>" + subcategory.getName() + "</td>");
                ps.println("</tr>");
            }

            ps.println("</tbody>");
            ps.println("</table>");

            ps.println("</body>");
            ps.println("</html>");

        }
    }
}
