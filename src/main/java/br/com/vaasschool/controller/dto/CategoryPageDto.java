package br.com.vaasschool.controller.dto;

import br.com.vaasschool.controller.model.Category;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryPageDto {

    final private String name;
    final private String imagePath;
    final private List<SubcategoryPageDto> subcategoryPageDtos;

    public CategoryPageDto(Category category) {
        this.name = category.getName();
        this.imagePath = category.getImagePath();
        this.subcategoryPageDtos = category.getActiveSubcategories().stream().map(SubcategoryPageDto::new).toList();
    }
}
