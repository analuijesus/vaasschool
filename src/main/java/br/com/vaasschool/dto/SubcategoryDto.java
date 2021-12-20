package br.com.vaasschool.dto;

public class SubcategoryDto {

    private int id;
    private String name;

    public SubcategoryDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
