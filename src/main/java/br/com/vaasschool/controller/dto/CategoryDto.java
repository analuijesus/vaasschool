package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Category;

import java.util.List;

public class CategoryDto {

    private Long id;
    private String name;
    private String code;
    private String description;
    private String explanatoryGuide;
    private Boolean active;
    private Integer order;
    private String imagePath;
    private String colorCode;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
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

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}