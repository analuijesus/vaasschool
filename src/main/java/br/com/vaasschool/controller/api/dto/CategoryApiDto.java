package br.com.vaasschool.controller.api.dto;

import br.com.vaasschool.model.Category;

import java.util.List;

public class CategoryApiDto {

    final private String name;
    final private String code;
    final private Integer order;
    final private String explanatoryGuide;
    final private String colorCode;
    final private int totalCourses;
    final private List<SubcategoryApiDto> subcategoryApiDtos;

    public CategoryApiDto(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.order = category.getOrder();
        this.explanatoryGuide = category.getExplanatoryGuide();
        this.colorCode = category.getColorCode();
        this.totalCourses = category.getNumberOfCourses();
        this.subcategoryApiDtos = category.getActiveSubcategories().stream().map(SubcategoryApiDto::new).toList();
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

    public String getExplanatoryGuide() {
        return explanatoryGuide;
    }

    public String getColorCode() {
        return colorCode;
    }

    public int getTotalCourses() {
        return totalCourses;
    }

    public List<SubcategoryApiDto> getSubcategoryApiDtos() {
        return subcategoryApiDtos;
    }
}
