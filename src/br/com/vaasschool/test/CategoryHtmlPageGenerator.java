package br.com.vaasschool.test;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.reader.CategoryReader;
import br.com.vaasschool.reader.CourseReader;
import br.com.vaasschool.reader.SubcategoryReader;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryHtmlPageGenerator {
    public static void main(String[] args) throws Exception {

        CategoryReader categoryReader = new CategoryReader();
        List<Category> categoryList = categoryReader.readCsvFile("C:\\Users\\anadejesus" +
                "\\Documents\\projetos-java\\vaas-school\\src\\planilha-dados-escola - Categoria.csv");

        Map<String, Category> categoryMap = new HashMap<>();
        for (Category category : categoryList) {
            categoryMap.put(category.getCode(), category);
        }

        SubcategoryReader subcategoryReader = new SubcategoryReader(categoryMap);
        List<Subcategory> subcategoryList = subcategoryReader.readCsvFile("C:\\Users\\anadejesus" +
                "\\Documents\\projetos-java\\vaas-school\\src\\planilha-dados-escola - Subcategoria.csv");

        Map<String, Subcategory> subcategoryMap = new HashMap<>();
        for (Subcategory subcategory : subcategoryList) {
            subcategoryMap.put(subcategory.getCode(), subcategory);
        }

        CourseReader courseReader = new CourseReader(subcategoryMap);
        List<Course> courseList = courseReader
                .readCsvFile("C:\\Users\\anadejesus\\Documents" +
                        "\\projetos-java\\vaas-school\\src\\planilha-dados-escola - Curso.csv");

        Map<String, Course> courseMap = new HashMap<>();
        for (Course course : courseList) {
            courseMap.put(course.getCode(), course);
        }

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
            ps.print("<th>Código</th>");
            ps.print("<th>Ordem</th>");
            ps.print("<th>Descrição</th>");
            ps.print("<th>Status</th>");
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
                ps.println("<td>" + c.getCode() + "<td>");
                ps.println("<td>" + c.getOrder() + "<td>");
                ps.println("<td>" + c.getDescription() + "<td>");
                ps.println("<td>" + c.getActive() + "<td>");
                ps.println("<td><img src=\"" + c.getImagePath() + "\"><td>");
                ps.println("<td>" + c.getColorCode() + "<td>");
                for (Subcategory sub : c.getSubCategoryList()) {
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
