package br.com.vaasschool.test;

import br.com.vaasschool.dao.CourseDao;
import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.util.JPAUtil;

import javax.persistence.EntityManager;

public class CourseDaoTest {

    public static void main(String[] args) {


        EntityManager entityManager = JPAUtil.getEntityManager();
        CourseDao courseDao = new CourseDao(entityManager);

        Category category = new Category("Programação", "programacao", "Programe nas principais linguagens e plataformas. Iniciantes são bem vindos nos cursos de lógica e JavaScript.",
                1, true, "https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png", "#00c86f");

        Subcategory subcategory = new Subcategory(2l, "Java e Persistência", "java-e-persistencia",
                "", true, 2, category);

        Course course = new Course("Java e JDBC: Trabalhando com um banco de dados", "java-e-jdbc",
                12, CourseVisibility.PUBLIC, "Desenvolvedores que já conheça Orientação a Objetos",
                "Ana de Jesus",
                "Comunique-se com um banco de dados relacional", "Evitando SQL Injection ", subcategory);

        try {
            entityManager.getTransaction().begin();

//            courseDao.save(course);
//            courseDao.delete("java-e-jdbc");
//            courseDao.updateAllForPublicVisibility();

//            List<Course> courses = courseDao.findAll();
//            courses.forEach(aCourse -> {
//                aCourse.publish();
//            });

//            Course jdbc = new Course(10L, "Java e JDBC: Trabalhando com um banco de dados", "java-e-jdbc",
//                    12, CourseVisibility.PUBLIC, "Desenvolvedores que já conheça Orientação a Objetos",
//                    "Ana de Jesus",
//                    "Comunique-se com um banco de dados relacional", "Evitando SQL Injection ", subcategory);
//            entityManager.merge(jdbc);

          Course jdbc = entityManager.find(Course.class, 10L);
          jdbc.publish();


            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
