package br.com.vaasschool.controller.api.dto;

import br.com.vaasschool.model.Category;
import lombok.Getter;

import java.util.List;

@Getter
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
}
