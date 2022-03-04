package br.com.vaasschool.controller.dto;

import br.com.vaasschool.controller.model.Category;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryLoginDto {

    final private String name;
    final private String code;
    final private String imagePath;
    final private List<SubcategoryLoginDto> subcategoryLoginDtos;

    public CategoryLoginDto(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.imagePath = category.getImagePath();
        this.subcategoryLoginDtos = category.getSubcategories().stream().map(SubcategoryLoginDto::new).toList();
    }

    public List<SubcategoryLoginDto> getSubcategoryLoginDtos() {
        return subcategoryLoginDtos.subList(0, subcategoryLoginDtos.size() < 3 ? subcategoryLoginDtos.size() : 3);
    }

    public int getSubcategorySize(){
       return this.subcategoryLoginDtos.size();
    }
}
