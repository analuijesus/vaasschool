package br.com.vaasschool.controller.model;

import br.com.vaasschool.controller.form.SubcategoryForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(onConstructor = @__(@Deprecated))
@Entity
public class Subcategory implements Comparable<Subcategory> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome da subcategoria deve ser preenchido.")
    private String name;
    @NotBlank(message = "O código da subcategoria é obrigatório.")
    @Pattern(regexp = "([a-z0-9^-]+)", message = "Insira um código válido. O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;
    private String description;
    @Column(name = "explanatory_guide")
    private String explanatoryGuide;
    private boolean active = false;
    @Column(name = "order_visualization")
    private Integer order;
    @NotNull(message = "A categoria deve ser preenchida.")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @OneToMany(mappedBy = "subcategory")
    private List<Course> courses = new ArrayList<>();

    public Subcategory(String name, String code, String description, String explanatoryGuide, boolean active, Integer order, Category category) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.explanatoryGuide = explanatoryGuide;
        this.active = active;
        this.order = order;
        this.category = category;
    }

    public int getTotalCourseHours() {
        return courses.stream().mapToInt(Course::getEstimatedTimeToFinish).sum();
    }

    public int getNumberOfCourses() {
        return courses.size();
    }

    public String getCategoryCode() {
        return category.getCode();
    }

    public String getCategoryName() {
        return category.getName();
    }

    public void update(SubcategoryForm subcategoryForm, Category category) {
        this.id = subcategoryForm.getId();
        this.name = subcategoryForm.getName();
        this.code = subcategoryForm.getCode();
        this.description = subcategoryForm.getDescription();
        this.explanatoryGuide = subcategoryForm.getExplanatoryGuide();
        this.active = subcategoryForm.isActive();
        this.order = subcategoryForm.getOrder();
        this.category = category;
    }

    @Override
    public int compareTo(Subcategory anotherSubCategory) {
        return this.order.compareTo(anotherSubCategory.order);
    }

}
