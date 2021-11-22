package br.com.vaasschool.test;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.reader.CategoryReader;
import br.com.vaasschool.reader.CourseReader;
import br.com.vaasschool.reader.SubcategoryReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseReaderTest {
    public static void main(String[] args) throws Exception {

        CategoryReader categoryReader = new CategoryReader();

        List<Category> categoryList = categoryReader
                .readCsvFile("planilha-dados-escola - Categoria.csv");

        Map<String, Category> categoryMap = new HashMap<>();
        for (Category category : categoryList) {
            categoryMap.put(category.getCode(), category);
        }

        SubcategoryReader subcategoryReader = new SubcategoryReader(categoryMap);
        List<Subcategory> subcategoryList = subcategoryReader
                .readCsvFile("planilha-dados-escola - Subcategoria.csv");


        Map<String, Subcategory> subcategoryMap = new HashMap<>();
        for (Subcategory subcategory : subcategoryList) {
            subcategoryMap.put(subcategory.getCode(), subcategory);
        }

        CourseReader courseReader = new CourseReader(subcategoryMap);
        List<Course> courseList = courseReader
                .readCsvFile("planilha-dados-escola - Curso.csv");

        Map<String, Course> courseMap = new HashMap<>();
        for (Course course : courseList) {
            courseMap.put(course.getCode(), course);
            System.out.println(course);
        }
    }
}
