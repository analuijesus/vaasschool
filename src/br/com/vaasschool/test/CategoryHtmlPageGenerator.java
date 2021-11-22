package br.com.vaasschool.test;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.reader.CategoryReader;
import br.com.vaasschool.reader.CourseReader;
import br.com.vaasschool.reader.SubcategoryReader;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryHtmlPageGenerator {
    public static void main(String[] args) throws Exception {
        loadData();
    }

    private static void loadData() throws Exception {
        CategoryReader categoryReader = new CategoryReader();
        List<Category> categoryList = categoryReader.readCsvFile("planilha-dados-escola - Categoria.csv");

        Map<String, Category> categoryMap = new HashMap<>();
        for (Category category : categoryList) {
            categoryMap.put(category.getCode(), category);
        }

        SubcategoryReader subcategoryReader = new SubcategoryReader(categoryMap);
        List<Subcategory> subcategoryList = subcategoryReader.readCsvFile("planilha-dados-escola - Subcategoria.csv");

        Map<String, Subcategory> subcategoryMap = new HashMap<>();
        for (Subcategory subcategory : subcategoryList) {
            subcategoryMap.put(subcategory.getCode(), subcategory);
        }

        CourseReader courseReader = new CourseReader(subcategoryMap);
        List<Course> courseList = courseReader
                .readCsvFile("planilha-dados-escola - Curso.csv");

        Map<String, Course> courseMap = new HashMap<>();
        for (Course course : courseList) {
            courseMap.put(course.getCode(), course);
        }

        writePage(categoryList);
    }

    private static void writePage(List<Category> categoryList) throws Exception {
        try (OutputStream os = new FileOutputStream("info-categoria.html")) {
            PrintStream ps = new PrintStream(os);

            ps.println("<html>");
            ps.println("<head>");
            ps.println("<meta charset=\"UTF-8\">");
            ps.println("</head>");
            ps.println("<body>");
            ps.println("<table>");

            ps.println("<thead>");
            ps.println("<tr>");

            ps.print("<th>Nome</th>");
            ps.print("<th>Descrição</th>");
            ps.print("<th>Status</th>");
            ps.print("<th>Total de horas</th>");
            ps.print("<th>Total de Cursos</th>");
            ps.print("<th>Ícone</th>");
            ps.print("<th>Cor</th>");
            ps.print("<th>Sub Nome</th>");
            ps.print("<th>Sub Cor</th>");
            ps.print("<th>Cursos</th>");
            ps.println("</tr>");
            ps.println("</thead>");
            ps.println("<tbody>");

            for (Category c : categoryList) {
                ps.println("<tr>");
                ps.println("<td>" + c.getName() + "<td>");
                ps.println("<td>" + c.getDescription() + "<td>");
                ps.println("<td>" + c.getActive() + "<td>");
                ps.println("<td>" + c.getTotalHoursCourse() + "<td>");
                ps.println("<td>" + c.getTotalCourse() + "<td>");
                ps.println("<td><img src=\"" + c.getImagePath() + "\"><td>");
                ps.println("<td>" + c.getColorCode() + "<td>");
                for (Subcategory sub : c.getActiveSubcategory()) {
                    ps.println("<td>" + sub.getName() + "<td>");
                    ps.println("<td>" + sub.getCode() + "<td>");
                    ps.println("<td>" + sub.getCourseNameList() + "<td>");
                }
                ps.println("</tr>");
            }

            ps.println("</tbody>");
            ps.println("</table>");
            ps.println("</body>");
            ps.println("</html>");

        }
    }
}
