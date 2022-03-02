package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Subcategory;
import lombok.Getter;

@Getter
public class SubcategoryLoginDto {

    final private String name;
    final private String code;

    public SubcategoryLoginDto(Subcategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
    }
}
