package br.com.vaasschool.reader;

import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.model.validation.Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CourseReader {

    private final Map<String, Subcategory> subcategoryMap;

    public CourseReader(Map<String, Subcategory> subcategoryMap) {
        Validator.notNull(subcategoryMap);
        this.subcategoryMap = subcategoryMap;
    }

    public List<Course> readCsvFile(String filePath) throws Exception {
        List<Course> courses = new ArrayList<>();

        try (BufferedReader br = new BufferedReader((new FileReader(filePath)))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {
                String[] courseData = line.split(",");
                Course course = courseStructure(courseData);

                courses.add(course);

                line = br.readLine();
            }
            return courses;
        }
    }

    private Course courseStructure(String[] vectorCourse) {
        String name = vectorCourse[0];
        String code = vectorCourse[1];
        int estimatedTimeToFinish = Integer.parseInt(vectorCourse[2]);
        CourseVisibility visibility = CourseVisibility.languageType(vectorCourse[3]);
        String targetAudience = vectorCourse[4];
        String instructorName = vectorCourse[5];
        String summary = vectorCourse[6];
        String learnedSkills = vectorCourse[7];
        String subcategoryCode = vectorCourse[8];

        Subcategory subcategory = this.subcategoryMap.get(subcategoryCode);
        Course course = new Course(name, code, estimatedTimeToFinish, visibility, targetAudience, instructorName, summary, learnedSkills, subcategory);

        subcategory.addCourse(course);
        return course;
    }

}



