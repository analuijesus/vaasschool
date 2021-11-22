package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Subcategory implements Comparable<Subcategory> {

    private String name;
    private String code;
    private String description;
    private String explanatoryGuide;
    private Boolean active = false;
    private Integer order;
    private Category category;
    private List<Course> courseList = new ArrayList<>();

    public Subcategory(String name, String code, String description, Boolean active, Integer order, Category category) {
        Validator.notNullOrEmpty(name);
        Validator.isCode(code);
        Validator.notNull(category);

        this.name = name;
        this.code = code;
        this.description = description;
        this.active = active;
        this.order = order;
        this.category = category;
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public String getCourseNameList(){
       return courseList.stream().map(c -> getName()).collect(Collectors.joining(" , "));
    }

    public void addCourse(Course course) {
        this.courseList.add(course);
    }

    public int getTotalHoursCourse(){
        return courseList.stream().mapToInt(c -> c.getEstimatedTimeToFinish()).sum();
    }

    public int getTotalCourse(){
        return (int) courseList.stream().count();
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", explanatoryGuide='" + explanatoryGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", category=" + category.getName() +
                '}';
    }

    @Override
    public int compareTo(Subcategory anotherSubCategory) {
        return this.order.compareTo(anotherSubCategory.order);
    }
}
