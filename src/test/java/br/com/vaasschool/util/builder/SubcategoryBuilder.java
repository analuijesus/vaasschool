package br.com.vaasschool.util.builder;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;

public class SubcategoryBuilder {

    private String name;
    private String code;
    private String description;
    private String explanatoryGuide;
    private Boolean active = false;
    private Integer order;
    private Category category;

    public SubcategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SubcategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public SubcategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public SubcategoryBuilder withExplanatoryGuide(String explanatoryGuide) {
        this.explanatoryGuide = explanatoryGuide;
        return this;
    }

    public SubcategoryBuilder withActive(Boolean active) {
        this.active = active;
        return this;
    }

    public SubcategoryBuilder withOrder(Integer order) {
        this.order = order;
        return this;
    }

    public SubcategoryBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public Subcategory create() {
        return new Subcategory(name, code, description, explanatoryGuide, active, order, category);
    }
}