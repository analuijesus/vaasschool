package br.com.vaasschool.model;

import br.com.vaasschool.model.validation.Validator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category implements Comparable<Category> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;

    @Column(name = "explanatory_guide")
    private String explanatoryGuide;
    private Boolean active = false;

    @Column(name = "order_visualization")
    private Integer order;

    @Column(name = "imagem_path")
    private String imagePath;

    @Column(name = "color_code")
    private String colorCode = "3383FF";

    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories = new ArrayList<>();

    @Deprecated
    public Category() {
    }

    public Category(String name, String code) {
        Validator.notNullOrEmpty(name);
        Validator.isCode(code);
        this.name = name;
        this.code = code;
    }

    public Category(String name, String code, String description, Integer order, Boolean active, String imagePath, String colorCode) {
        this(name, code);
        this.description = description;
        this.order = order;
        this.active = active;
        this.imagePath = imagePath;
        this.colorCode = colorCode;
    }

    public Long getId() {
        return id;
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

    public Boolean getActive() {
        return active;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getColorCode() {
        return colorCode;
    }

    public Integer getOrder() {
        return order;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setExplanatoryGuide(String explanatoryGuide) {
        this.explanatoryGuide = explanatoryGuide;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void deactivate(){
        this.active = false;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void addSubcategory(Subcategory subcategory) {
        this.subcategories.add(subcategory);
    }

    public int getTotalCourseHours() {
        return subcategories.stream().mapToInt(Subcategory::getTotalCourseHours).sum();
    }

    public int getNumberOfCourses() {
        return subcategories.stream().mapToInt(Subcategory::getNumberOfCourses).sum();
    }

    public List<Subcategory> getActiveSubcategories() {
        return subcategories.stream()
                .filter(Subcategory::getActive)
                .toList();
    }

    @Override
    public int compareTo(Category anotherCategory) {
        return this.order.compareTo(anotherCategory.order);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", explanatoryGuide='" + explanatoryGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", imagePath='" + imagePath + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }
}
