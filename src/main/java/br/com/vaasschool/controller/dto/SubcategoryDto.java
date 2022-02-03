package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Subcategory;

public class SubcategoryDto {

    final private Long id;
    final private String name;
    final private String code;
    final private String description;
    final private String explanatoryGuide;
    final private Boolean active;
    final private Integer order;
    final private String categoryName;

    public SubcategoryDto(Subcategory subcategory) {
        this.id = subcategory.getId();
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.description = subcategory.getDescription();
        this.explanatoryGuide = subcategory.getExplanatoryGuide();
        this.active = subcategory.getActive();
        this.order = subcategory.getOrder();
        this.categoryName = subcategory.getCategory().getName(); //ENCAPSULAR
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getExplanatoryGuide() {
        return explanatoryGuide;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getOrder() {
        return order;
    }
}
