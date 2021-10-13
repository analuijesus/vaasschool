package br.com.vaasschool.test;

import br.com.vaasschool.model.Course;

public class CourseTest {
    public static void main(String[] args) {
        Course c = new Course("java", "-java", null, "Ana");


        c.setDescription("studying java OO");
        c.setLearnedSkill("program in java");

        System.out.println(c.getName() + " - " + c.getCode() + " - " + c.getTimeCouse() + " - " + c.getInstructorName());

    }
}
