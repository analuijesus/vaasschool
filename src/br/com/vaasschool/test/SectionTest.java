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

        Section section1 = new Section("java teste", "java-", javaCourse);
        Section section2 = new Section("iniciando estados", "iniciante", javaCourse);
        Section section3 = new Section("anotações", "anotacoes", javaCourse);
        Section section4 = new Section("novo teste", "java-novoteste", javaCourse);


        List<Section> sections = Arrays.asList(section1, section2, section3, section4);
        Collections.sort(sections);
        System.out.println(sections);

    }
}
