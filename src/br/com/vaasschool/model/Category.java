package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Category implements Comparable<Category> {

    private String name;
    private String code;
    private String description;
    private String explanatoryGuide;
    private Boolean active = false;
    private Integer order;
    private String imagePath;
    private String colorCode = "3383FF";
    private List<Subcategory> subcategoryList = new ArrayList<>();

    public Category(String name) {
        Validator.notNullOrEmpty(name);
        this.name = name;
    }

    public Category(String name, String code) {
        this(name);
        Validator.isCode(code);
        this.code = code;
    }

    public Category(String name, String code, String description, Integer order, Boolean active, String imagePath, String colorCode) {
        this(name, code);
        this.description = description;
        this.order = order;
        this.active = active;
        this.imagePath = imagePath;
        this.colorCode = colorCode;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return active;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getColorCode() {
        return colorCode;
    }

    public List<Subcategory> getSubcategoryList() {
        return subcategoryList;
    }

    public void addSubcategory(Subcategory subcategory) {
        this.subcategoryList.add(subcategory);
    }

    public int getTotalHoursCourse(){
        return subcategoryList.stream().mapToInt(Subcategory::getTotalHoursCourse).sum();
    }

    public int getTotalCourse(){
        return subcategoryList.stream().mapToInt(Subcategory::getTotalCourse).sum();
    }

    public List<Subcategory> getActiveSubcategory(){
        return subcategoryList.stream()
                .filter(Subcategory::getActive)
                .toList();
    }

    @Override
    public int compareTo(Category anotherCategory) {
        return this.order.compareTo(anotherCategory.order);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", explanatoryGuide='" + explanatoryGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", imagePath='" + imagePath + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }
}
