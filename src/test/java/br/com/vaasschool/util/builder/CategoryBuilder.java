package br.com.vaasschool.util.builder;

import br.com.vaasschool.model.Category;

public class CategoryBuilder {

    private String name;
    private String code;
    private String description;
    private Boolean active = false;
    private Integer order;
    private String imagePath;
    private String colorCode = "3383FF";

    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryBuilder withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public CategoryBuilder withOrder(Integer order) {
        this.order = order;
        return this;
    }

    public CategoryBuilder withImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public CategoryBuilder withColorCode(String colorCode) {
        this.colorCode = colorCode;
        return this;
    }

    public Category create() {
        return new Category(name, code, description, order, active, imagePath, colorCode);
    }
}
