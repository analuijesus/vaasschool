package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

public abstract class Activity implements Comparable<Activity> {

    private String title;
    private String code;
    private Integer order;
    private Boolean active = false;
    private Section section;

    public Activity(String title, String code, Section section) {
        Validator.notNull(section);
        Validator.notNullOrEmpty(title);
        Validator.isCode(code);

        this.title = title;
        this.code = code;
        this.section = section;
    }

    public String getCode() {
        return code;
    }

    @Override
    public int compareTo(Activity anotherActivity) {
        return this.order.compareTo(anotherActivity.order);
    }
}
