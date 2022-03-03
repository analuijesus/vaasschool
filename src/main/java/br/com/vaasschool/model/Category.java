package br.com.vaasschool.model;

import br.com.vaasschool.controller.form.CategoryForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(onConstructor = @__(@Deprecated))
@Entity
public class Category implements Comparable<Category> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome da categoria precisa ser preenchida.")
    private String name;
    @NotBlank(message = "O código da categoria é obrigatório.")
    @Pattern(regexp = "([a-z0-9^-]+)", message = "Insira um código válido. O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;
    private String description;
    @Column(name = "explanatory_guide")
    private String explanatoryGuide;
    private Boolean active = false;
    @Column(name = "order_visualization")
    private Integer order;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "color_code")
    private String colorCode = "3383FF";

    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories = new ArrayList<>();

    public Category(String name, String code) {
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

    public Category(String name, String code, String description, String explanatoryGuide, Integer order, Boolean active, String imagePath, String colorCode) {
        this(name, code, description, order, active, imagePath, colorCode);
        this.explanatoryGuide = explanatoryGuide;
    }

    public int getTotalCourseHours() {
        return subcategories.stream().mapToInt(Subcategory::getTotalCourseHours).sum();
    }

    public int getNumberOfCourses() {
        return subcategories.stream().mapToInt(Subcategory::getNumberOfCourses).sum();
    }

    public List<Subcategory> getActiveSubcategories() {
        return subcategories.stream()
                .filter(Subcategory::isActive)
                .toList();
    }

    public void update(CategoryForm categoryForm) {
        this.id = categoryForm.getId();
        this.name = categoryForm.getName();
        this.code = categoryForm.getCode();
        this.description = categoryForm.getDescription();
        this.explanatoryGuide = categoryForm.getExplanatoryGuide();
        this.active = categoryForm.getActive();
        this.order = categoryForm.getOrder();
        this.imagePath = categoryForm.getImagePath();
        this.colorCode = categoryForm.getColorCode();
    }

    @Override
    public int compareTo(Category anotherCategory) {
        return this.order.compareTo(anotherCategory.order);
    }
}
