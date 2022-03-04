package br.com.vaasschool.controller.dto;

import br.com.vaasschool.controller.model.Category;
import lombok.Getter;

@Getter
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
}