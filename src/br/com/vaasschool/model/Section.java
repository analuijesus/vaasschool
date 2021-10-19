package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public class Section implements Comparable<Section>{

    private String name;
    private String code;
    private Boolean active = false;
    private Boolean test = false;
    private Integer order;
    private Course course;

    public Section(String name, String code, Course course) {

        Validator.nullValidation(course);
        Validator.writtenFieldValidation(name);
        Validator.codeValidation(code);

        this.name = name;
        this.code = code;
        this.course = course;
    }

    public Integer getOrder() {
        return order;
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

    public Boolean getActive() {
        return active;
    }

    public Boolean getTest() {
        return test;
    }

    public Course getCourse() {
        return course;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }

    @Override
    public int compareTo(Section anotherSection) {
        return this.order.compareTo(anotherSection.order);
    }
}
