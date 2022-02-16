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

    public CategoryForm(Long id, String name, String code, String description, String explanatoryGuide, Boolean active, Integer order, String imagePath, String colorCode) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.explanatoryGuide = explanatoryGuide;
        this.active = active;
        this.order = order;
        this.imagePath = imagePath;
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

    public Category convert(CategoryRepository categoryRepository) {
        Category category = categoryRepository.findById(getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, String.format("Category %s not found", code)));

        category.setId(getId());
        category.setName(getName());
        category.setCode(getCode());
        category.setDescription(getDescription());
        category.setExplanatoryGuide(getExplanatoryGuide());
        category.setActive(getActive());
        category.setOrder(getOrder());
        category.setImagePath(getImagePath());
        category.setColorCode(getColorCode());

        return category;
    }

    public static CategoryForm from(Category category) {
        return new CategoryForm(category.getId(), category.getName(), category.getCode(), category.getDescription(),
                category.getExplanatoryGuide(), category.getActive(), category.getOrder(), category.getImagePath(),
                category.getColorCode());
    }
}