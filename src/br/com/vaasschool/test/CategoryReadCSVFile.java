package br.com.vaasschool.test;

import br.com.vaasschool.model.Category;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CategoryReadCSVFile {
    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\anadejesus\\Documents\\projetos-java\\vaas-school\\src\\planilha-dados-escola - Categoria.csv";

        BufferedReader br = new BufferedReader((new FileReader(path)));
        List<Category> list = new ArrayList<>();

        String line = br.readLine();
        line = br.readLine();

        while (line != null) {

            String[] vetorCategory = line.split(",");

            String name = vetorCategory[0];
            String code = vetorCategory[1];
            Integer order = Integer.parseInt(vetorCategory[2].trim());
            String description = vetorCategory[3];
            Boolean active = Boolean.parseBoolean(vetorCategory[4]);
            String imagePath = vetorCategory[5];
            String colorCode = vetorCategory[6];

            Category category = new Category(name, code);
            category.setDescription(description);
            category.setActive(active);
            category.setOrder(order);
            category.setImagePath(imagePath);
            category.setColorCode(colorCode);

            list.add(category);

            line = br.readLine();
        }
            list.forEach(c -> System.out.println(c));

    }
}
