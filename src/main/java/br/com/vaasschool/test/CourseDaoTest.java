package br.com.vaasschool.test;

import br.com.vaasschool.dao.CourseDAO;
import br.com.vaasschool.dao.factory.ConnectionFactory;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;
import br.com.vaasschool.model.Subcategory;

import java.sql.Connection;
import java.sql.SQLException;

public class CourseDaoTest {

    public static void main(String[] args) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            connection.setAutoCommit(false);

            try {

                CourseDAO courseDao = new CourseDAO(connection);

                Category category = new Category("Programação", "programacao", "Programe nas principais linguagens e plataformas. Iniciantes são bem vindos nos cursos de lógica e JavaScript.",
                        1, true, " https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png", "#00c86f");

                Subcategory subcategory = new Subcategory(2, "Java e Persistência", "java-e-persistencia",
                        "", true, 2, category);

                Course course = new Course("Java e JDBC: Trabalhando com um banco de dados", "java-e-jdbc",
                        12, CourseVisibility.PUBLIC, "Desenvolvedores que já conheça Orientação a Objetos",
                        "Ana de Jesus",
                        "Comunique-se com um banco de dados relacional", "Evitando SQL Injection ", subcategory);

                int updatedCourses = courseDao.updatesAllForPublicVisibility();

//                courseDao.add(course);
                //            courseDao.delete("git-e-github-para-sobrevivencia");

                connection.commit();

                System.out.println("Total de curso com a visibilidade alterada: " + updatedCourses);

            } catch (Exception ex) {
                connection.rollback();
                throw ex;
            }
        }
    }
}
