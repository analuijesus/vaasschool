package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class Category implements Comparable<Category> {

    private String name;
    private String code;
    private String description;
    private String explanatoryGuide;
    private Boolean active = false;
    private Integer order;
    private String imagePath;
    private String colorCode = "3383FF";
    private List<Subcategory> subCategoryList = new ArrayList<>();

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

    public Integer getOrder() {
        return order;
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

    public List<Subcategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void addSubcategory(Subcategory subCategory) {
        this.subCategoryList.add(subCategory);
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
