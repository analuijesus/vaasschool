package br.com.vaasschool.reader;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.model.validation.Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class SubcategoryReader {

    private final Map<String, Category> categoryMap;

    public SubcategoryReader(Map<String, Category> categoryMap) {
        Validator.notNull(categoryMap);
        this.categoryMap = categoryMap;
    }

    public List<Subcategory> readCsvFile(String filePath) throws Exception {
        List<Subcategory> subcategories = new ArrayList<>();

        try (BufferedReader br = new BufferedReader((new FileReader(filePath)))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {
                String[] subcategoryData = line.split(",");
                Subcategory subcategory = read(subcategoryData);

                subcategories.add(subcategory);

                line = br.readLine();
            }
            return subcategories;
        }
    }

    public Subcategory read(String[] subcategoryData) {
        String name = subcategoryData[0];
        String code = subcategoryData[1];
        int order = Integer.parseInt(subcategoryData[2]);
        String description = subcategoryData[3];
        boolean active = subcategoryData[4].equalsIgnoreCase("ATIVA");

        String categoryCode = subcategoryData[5];

        Category category = this.categoryMap.get(categoryCode);
        Subcategory subcategory = new Subcategory(name, code, description, active, order, category);

        category.addSubcategory(subcategory);
        return subcategory;
    }
}
