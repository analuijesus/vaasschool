package br.com.vaasschool.filegenerator;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.reader.CategoryReader;
import br.com.vaasschool.reader.CourseReader;
import br.com.vaasschool.reader.SubcategoryReader;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class SqlDataGenerator {

    public static void main(String[] args) throws Exception {
        loadData();
    }

    private static void loadData() throws Exception {
        CategoryReader categoryReader = new CategoryReader();
        List<Category> categories = categoryReader.readCsvFile("planilha-dados-escola - Categoria.csv");

        Map<String, Category> categoryMap = new HashMap<>();
        for (Category category : categories) {
            categoryMap.put(category.getCode(), category);
        }

        SubcategoryReader subcategoryReader = new SubcategoryReader(categoryMap);
        List<Subcategory> subcategories = subcategoryReader.readCsvFile("planilha-dados-escola - Subcategoria.csv");

        Map<String, Subcategory> subcategoryMap = new HashMap<>();
        for (Subcategory subcategory : subcategories) {
            subcategoryMap.put(subcategory.getCode(), subcategory);
        }

        CourseReader courseReader = new CourseReader(subcategoryMap);
        List<Course> courses = courseReader.readCsvFile("planilha-dados-escola - Curso.csv");

        Map<String, Course> courseMap = new HashMap<>();
        for (Course course : courses) {
            courseMap.put(course.getCode(), course);
        }

        try (OutputStream os = new FileOutputStream("vaasschool_dados.sql")) {
            PrintStream ps = new PrintStream(os);

            writeCategoriesInsert(categories, ps);
            writeSubcategoriesInsert(subcategories, ps);
            writeCoursesInsert(courses, ps);
        }
    }

    private static void writeCoursesInsert(List<Course> courses, PrintStream ps) {
        for (Course course : courses) {
            String name = course.getName();
            String code = course.getCode();
            Integer estimatedTimeToFinish = course.getEstimatedTimeToFinish();
            CourseVisibility visibility = course.getVisibility();
            String targetAudience = course.getTargetAudience();
            String instructorName = course.getInstructorName();
            String summary = course.getSummary();
            String learnedSkills = course.getLearnedSkills();
            Subcategory subcategory = course.getSubcategory();

            String sqlCourse = "insert into Course (name, code, estimated_time_to_finish, visibility, target_audience, " +
                    "instructor_name, summary, learned_skills, subcategory_id) values ('%s', '%s', %s, '%s', '%s', " +
                    "'%s', '%s', '%s', (select id from Subcategory where code = '%s'));";

            ps.println(format(sqlCourse, name, code, estimatedTimeToFinish, visibility, targetAudience,
                    instructorName, summary, learnedSkills, subcategory.getCode()));

        }
    }

    private static void writeSubcategoriesInsert(List<Subcategory> subcategories, PrintStream ps) {
        for (Subcategory subcategory : subcategories) {
            String name = subcategory.getName();
            String code = subcategory.getCode();
            Integer order = subcategory.getOrder();
            String description = subcategory.getDescription();
            boolean active = subcategory.getActive();
            Category category = subcategory.getCategory();

            String sqlSubcategory = "insert into Subcategory (name, code, `order`, description, active, category_id)" +
                    " values ('%s', '%s', %s, '%s', %s, (select id from Category where code = '%s'));";

            ps.println(format(sqlSubcategory, name, code, order, description, active, category.getCode()));
        }
    }

    private static void writeCategoriesInsert(List<Category> categories, PrintStream ps) {
        for (Category category : categories) {
            String name = category.getName();
            String code = category.getCode();
            Integer order = category.getOrder();
            String description = category.getDescription();
            boolean active = category.getActive();
            String imagePath = category.getImagePath();
            String colorCode = category.getColorCode();

            String sqlCategory = "insert into Category (name, code, `order`, description, active, imagem_path, color_code)" +
                    " values ('%s', '%s', %s, '%s', %s, '%s', '%s');";

            ps.println(format(sqlCategory, name, code, order, description, active, imagePath, colorCode));
        }
    }
}
