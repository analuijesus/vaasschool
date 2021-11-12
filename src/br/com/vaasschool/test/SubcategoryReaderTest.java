package br.com.vaasschool.test;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.reader.CategoryReader;
import br.com.vaasschool.reader.SubcategoryReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubcategoryReaderTest {
    public static void main(String[] args) throws Exception {

        CategoryReader categoryReader = new CategoryReader();
        List<Category> categoryList = categoryReader
                .readCsvFile("C:\\Users\\anadejesus" +
                        "\\Documents\\projetos-java\\vaas-school\\src\\planilha-dados-escola - Categoria.csv");

        Map<String, Category> categoryMap = new HashMap<>();
        for (Category category: categoryList) {
            categoryMap.put(category.getCode(), category);
        }

        SubcategoryReader subcategoryReader = new SubcategoryReader(categoryMap);
        List<Subcategory> subcategoryList = subcategoryReader
                .readCsvFile("C:\\Users\\anadejesus" +
                        "\\Documents\\projetos-java\\vaas-school\\src\\planilha-dados-escola - Subcategoria.csv");


        Map<String, Subcategory> subcategoryMap = new HashMap<>();
        for (Subcategory subcategory : subcategoryList) {
            subcategoryMap.put(subcategory.getCode(), subcategory);
            System.out.println(subcategory);
        }
    }
}
