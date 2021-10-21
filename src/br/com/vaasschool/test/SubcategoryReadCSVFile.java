package br.com.vaasschool.test;


import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubcategoryReadCSVFile {
    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\anadejesus\\Documents\\projetos-java\\vaas-school\\src\\planilha-dados-escola - Subcategoria.csv";

        BufferedReader br = new BufferedReader((new FileReader(path)));
        List<Subcategory> list = new ArrayList<>();


        String line = br.readLine();
        line = br.readLine();

        while (line != null) {

            String[] vetorSubcategory = line.split(",");

            String nameSubcategory = vetorSubcategory[0];
            String codeSubcategory = vetorSubcategory[1];
            Integer order = Integer.parseInt(vetorSubcategory[2]);
            String description = vetorSubcategory[3];
            Boolean active = Boolean.parseBoolean(vetorSubcategory[4]);

            String[] vetorCategory = line.split(",");

            String nameCategory = vetorCategory[0];

            Category category = new Category(nameCategory);

            Subcategory subcategory = new Subcategory(nameSubcategory, codeSubcategory, category);

            list.add(subcategory);

            line = br.readLine();
        }
        list.forEach(c -> System.out.println(c));

    }
}
