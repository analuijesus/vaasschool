package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Category;

import java.util.List;

public class CategoryLoginDto {

    private String name;
    private String code;
    private String imagePath;
    private List<SubcategoryLoginDto> subcategoryLoginDtos;

    public CategoryLoginDto(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.imagePath = category.getImagePath();
        this.subcategoryLoginDtos = category.getSubcategories().stream().map(SubcategoryLoginDto::new).toList();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getImagePath() {
        return imagePath;
    }

    public List<SubcategoryLoginDto> getSubcategoryLoginDtos() {
        return subcategoryLoginDtos.subList(0, subcategoryLoginDtos.size() < 3 ? subcategoryLoginDtos.size() : 3);
    }

    public int getSubcategorySize(){
       return this.subcategoryLoginDtos.size();
    }
}
