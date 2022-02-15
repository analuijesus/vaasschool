package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Category;

import java.util.List;

public class CategoryPageDto {

    private final String name;
    private final String imagePath;
    private final List<SubcategoryPageDto> subcategoryPageDtos;

    public CategoryPageDto(Category category) {
        this.name = category.getName();
        this.imagePath = category.getImagePath();
        this.subcategoryPageDtos = category.getActiveSubcategories().stream().map(SubcategoryPageDto::new).toList();
    }

    public String getName() {
        return name;
    }

    public List<SubcategoryPageDto> getSubcategoryPageDtos() {
        return subcategoryPageDtos;
    }

    public String getImagePath() {
        return imagePath;
    }
}
