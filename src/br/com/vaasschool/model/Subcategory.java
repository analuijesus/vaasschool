package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public class Subcategory implements Comparable<Subcategory>{

    private String name;
    private String code;
    private String description;
    private String explanatoryGuide;
    private Boolean active = false;
    private Integer order;
    private Category category;
    private Course course;

    public Subcategory(String name, String code, Category category) {
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public Subcategory(String name, String code, Category category, Course course) {

        Validator.notNull(course);
        Validator.notNullOrEmpty(name);
        Validator.isCode(code);

        this.name = name;
        this.code = code;
        this.category = category;
        this.course = course;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExplanatoryGuide(String explanatoryGuide) {
        this.explanatoryGuide = explanatoryGuide;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public String getExplanatoryGuide() {
        return explanatoryGuide;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getOrder() {
        return order;
    }

    public Category getCategory() {
        return category;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public int compareTo(Subcategory anotherSubCategory) {
        return this.order.compareTo(anotherSubCategory.order);
    }
}
