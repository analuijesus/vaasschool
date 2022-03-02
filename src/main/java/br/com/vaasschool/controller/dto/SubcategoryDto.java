package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Subcategory;
import lombok.Getter;

@Getter
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
        this.active = subcategory.isActive();
        this.order = subcategory.getOrder();
        this.categoryName = subcategory.getCategoryName();
    }
}
