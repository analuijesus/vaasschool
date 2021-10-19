package br.com.vaasschool.test;

import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Section;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SectionTest {
    public static void main(String[] args) {

        //Course c = new Course("java", "-java", 20, "Ana");

        Section s1 = new Section("java teste", "java-", null);
        Section s2 = new Section("iniciando estados", "iniciante", c);
        Section s3 = new Section("anotações", "anotacoes", c);
        Section s4 = new Section("novo teste", "java-novoteste", c);
        s1.setOrder(3);
        s2.setOrder(2);
        s3.setOrder(10);
        s4.setOrder(1);

        List list = Arrays.asList(s1, s2, s3, s4);

        Collections.sort(list);

        System.out.println(list);

    }
}
