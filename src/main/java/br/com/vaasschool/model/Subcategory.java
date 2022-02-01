package br.com.vaasschool.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Subcategory implements Comparable<Subcategory> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da subcategoria deve ser preenchido.")
    private String name;

    @NotBlank(message = "Insira um código válido.O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;
    private String description;

    @Column(name = "explanatory_guide")
    private String explanatoryGuide;
    private Boolean active = false;

    @Column(name = "order_visualization")
    private Integer order;

    @NotNull(message = "A categoria deve ser preenchida.")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "subcategory")
    private List<Course> courses = new ArrayList<>();

    @Deprecated
    public Subcategory() {
    }

    public Subcategory(String name, String code, String description, Boolean active, Integer order, Category category) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.active = active;
        this.order = order;
        this.category = category;
    }

    public Subcategory(Long id, String name, String code, String description, Boolean active, Integer order, Category category) {
        this(name, code, description, active, order, category);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getOrder() {
        return order;
    }

    public Category getCategory() {
        return category;
    }

    public String getExplanatoryGuide() {
        return explanatoryGuide;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String getCourseNames() {
        return courses.stream().map(Course::getName).collect(Collectors.joining(" , "));
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public int getTotalCourseHours() {
        return courses.stream().mapToInt(Course::getEstimatedTimeToFinish).sum();
    }

    public int getNumberOfCourses() {
        return courses.size();
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", explanatoryGuide='" + explanatoryGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", category=" + category.getName() +
                '}';
    }

    @Override
    public int compareTo(Subcategory anotherSubCategory) {
        return this.order.compareTo(anotherSubCategory.order);
    }

}
