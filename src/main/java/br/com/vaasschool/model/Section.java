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
        Validator.notNull(course);
        Validator.notNullOrEmpty(name);
        Validator.isCode(code);

        this.name = name;
        this.code = code;
        this.course = course;
    }

    public String getCode() {
        return code;
    }

    @Override
    public int compareTo(Section anotherSection) {
        return this.order.compareTo(anotherSection.order);
    }
}
