package br.com.vaasschool.controller.form;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.repository.CategoryRepository;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class CategoryForm {

    private Long id;

    @NotBlank(message = "O nome da categoria precisa ser preenchida.")
    private String name;

    @NotBlank(message = "O código da categoria é obrigatório.")
    @Pattern(regexp = "([a-z0-9^-]+)", message = "Insira um código válido. O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;
    private String description;
    private String explanatoryGuide;
    private Boolean active = false;
    @Positive(message = "A ordem deve ser um número positivo ou vazio.")
    private Integer order;
    private String imagePath;
    private String colorCode;

    public CategoryForm() {
    }

    public CategoryForm(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.description = category.getDescription();
        this.explanatoryGuide = category.getExplanatoryGuide();
        this.active = category.getActive();
        this.order = category.getOrder();
        this.imagePath = category.getImagePath();
        this.colorCode = category.getColorCode();
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

    public Boolean getActive() {
        return active;
    }

    public Integer getOrder() {
        return order;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getColorCode() {
        return colorCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExplanatoryGuide(String explanatoryGuide) {
        this.explanatoryGuide = explanatoryGuide;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Category toModel() {
        return new Category(name, code, description, explanatoryGuide, order, active, imagePath, colorCode);
    }
}