package br.com.vaasschool.test;

import br.com.vaasschool.reader.CategoryReader;
import br.com.vaasschool.model.Category;

import java.util.List;

public class CategoryReaderTest {
    public static void main(String[] args) throws Exception {

        CategoryReader categoryReader = new CategoryReader();
        List<Category> categories = categoryReader.readCsvFile("C:\\Users\\anadejesus\\Documents\\projetos-java\\vaas-school\\src\\planilha-dados-escola - Categoria.csv");

        for (Category c: categories) {
            System.out.println(c);
        }
    }
}
