package br.com.vaasschool.test;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Section;
import br.com.vaasschool.model.Subcategory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SectionTest {
    public static void main(String[] args) {

        Category category = new Category("Java Teste", "java-teste");
        Subcategory subcategory = new Subcategory("JavaOO", "java-oo","a linguagem para se trabalhar", true, 3, category);
        Course javaCourse = new Course("java", "-java", 20, "Ana", subcategory);

        Section s1 = new Section("java teste", "java-", javaCourse);
        Section s2 = new Section("iniciando estados", "iniciante", javaCourse);
        Section s3 = new Section("anotações", "anotacoes", javaCourse);
        Section s4 = new Section("novo teste", "java-novoteste", javaCourse);


        List list = Arrays.asList(s1, s2, s3, s4);
        Collections.sort(list);
        System.out.println(list);

    }
}
