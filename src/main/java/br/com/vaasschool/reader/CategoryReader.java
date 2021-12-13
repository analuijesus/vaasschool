package br.com.vaasschool.reader;

import br.com.vaasschool.model.Category;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CategoryReader {

    public List<Category> readCsvFile(String filePath) throws Exception {
        List<Category> categories = new ArrayList<>();

        try (BufferedReader br = new BufferedReader((new FileReader(filePath)))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {
                String[] categoryData = line.split(",");
                Category category = read(categoryData);
                categories.add(category);
                line = br.readLine();
            }
            return categories;
        }
    }

    public Category read(String[] categoryData) {
        String name = categoryData[0];
        String code = categoryData[1];
        int order = Integer.parseInt(categoryData[2].trim());
        String description = categoryData[3];
        boolean active = categoryData[4].equalsIgnoreCase("ATIVA");
        String imagePath = categoryData[5];
        String colorCode = categoryData[6];

        return new Category(name, code, description, order, active, imagePath, colorCode);
    }
}
