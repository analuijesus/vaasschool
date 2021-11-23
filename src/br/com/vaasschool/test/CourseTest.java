package br.com.vaasschool.test;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;

public class CourseTest {
    public static void main(String[] args) {
        Category category = new Category("JavaOO", "java-oo");
        Subcategory subcategory = new Subcategory("Javinha Teste", "java-teste", "teste", true, 15, category);
        Course javaCourse = new Course("Java", "java-8", 2, "Ana", subcategory);

        System.out.println(javaCourse);

    }
}
