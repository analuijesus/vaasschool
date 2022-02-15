package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Category;

public class CategoryDto {

    final private Long id;
    final private String name;
    final private String code;
    final private String description;
    final private String explanatoryGuide;
    final private Boolean active;
    final private Integer order;
    final private String imagePath;
    final private String colorCode;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.description = category.getDescription();
        this.explanatoryGuide = category.getExplanatoryGuide();
        this.order = category.getOrder();
        this.active = category.getActive();
        this.imagePath = category.getImagePath();
        this.colorCode = category.getColorCode();
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

    public String getImagePath() {
        return imagePath;
    }

    public String getColorCode() {
        return colorCode;
    }

}