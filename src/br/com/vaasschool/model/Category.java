package br.com.vaasschool.model;

public class Category  implements Comparable<Category>{

    private String name;
    private String code;
    private String description;
    private String explanatoryGuide;
    private Boolean activeCategory = false;
    private Integer order;
    private String pathImage;
    private String colorCode = "3383FF";

    public Category(String name, String code) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        boolean validateCode = code.matches("([a-z^-]+)");
        if (!validateCode) {
            throw new IllegalArgumentException("Código inválido");
        }

        this.name = name;
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExplanatoryGuide(String explanatoryGuide) {
        this.explanatoryGuide = explanatoryGuide;
    }

    public void setActiveCategory(Boolean activeCategory) {
        this.activeCategory = activeCategory;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getExplanatoryGuide() {
        return explanatoryGuide;
    }

    public Boolean getActiveCategory() {
        return activeCategory;
    }

    public Integer getOrder() {
        return order;
    }

    public String getPathImage() {
        return pathImage;
    }

    public String getColorCode() {
        return colorCode;
    }

    @Override
    public int compareTo(Category anotherCategory) {
        return this.order.compareTo(anotherCategory.order);
    }
}
