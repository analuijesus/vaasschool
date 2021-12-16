package br.com.vaasschool.dto;

public class CourseDto {

    private int id;
    private String name;
    private int estimatedTimeToFinish;
    private SubcategoryDto subcategoryDto;

    public CourseDto(int id, String name, int estimatedTimeToFinish, SubcategoryDto subcategoryDto) {
        this.id = id;
        this.name = name;
        this.estimatedTimeToFinish = estimatedTimeToFinish;
        this.subcategoryDto = subcategoryDto;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getEstimatedTimeToFinish() {
        return estimatedTimeToFinish;
    }

    public SubcategoryDto getSubcategoryDto() {
        return subcategoryDto;
    }

}
