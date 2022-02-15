package br.com.vaasschool.controller.dto;

import br.com.vaasschool.model.Subcategory;

public class SubcategoryLoginDto {

    private String name;
    private String code;

    public SubcategoryLoginDto(Subcategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
