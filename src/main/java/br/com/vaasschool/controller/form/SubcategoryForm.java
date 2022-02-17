package br.com.vaasschool.controller.form;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;
import br.com.vaasschool.repository.SubcategoryRepository;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class SubcategoryForm {

    private Long id;
    @NotBlank(message = "O nome da subcategoria precisa ser preenchida.")
    private String name;
    @NotBlank(message = "O código da subcategoria é obrigatório.")
    @Pattern(regexp = "([a-z0-9^-]+)", message = "Insira um código válido. O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;
    private String description;
    private String explanatoryGuide;
    private boolean active;
    @Positive(message = "A ordem deve ser um número positivo ou vazio.")
    private Integer order;
    @NotNull(message = "A subcategoria deve ter uma categoria associada.")
    private Long categoryId;
    private String categoryName;

    public SubcategoryForm() {
    }

    public SubcategoryForm(Long id, String name, String code, String description, String explanatoryGuide, boolean active,
                           Integer order, Long categoryId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.explanatoryGuide = explanatoryGuide;
        this.active = active;
        this.order = order;
        this.categoryId = categoryId;
    }

    public boolean isActive() {
        return active;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getExplanatoryGuide() {
        return explanatoryGuide;
    }

    public boolean getActive() {
        return active;
    }

    public Integer getOrder() {
        return order;
    }

    public Long getCategoryId() {
        return categoryId;
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Subcategory toModel(Category category) {
        return new Subcategory(name, code, description, explanatoryGuide, active, order, category);
    }

    public Subcategory convert(SubcategoryRepository subcategoryRepository) {
        Subcategory subcategory = subcategoryRepository.findById(getId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, String.format("Category %s not found", code)));

        subcategory.setId(id);
        subcategory.setName(name);
        subcategory.setCode(code);
        subcategory.setDescription(description);
        subcategory.setExplanatoryGuide(explanatoryGuide);
        subcategory.setActive(active);
        subcategory.setOrder(order);
        subcategory.setCategory(subcategory.getCategory());

        return subcategory;
    }

    public static SubcategoryForm from(Subcategory subcategory) {
        return new SubcategoryForm(subcategory.getId(), subcategory.getName(), subcategory.getCode(), subcategory.getDescription(),
                subcategory.getExplanatoryGuide(), subcategory.getActive(), subcategory.getOrder(), subcategory.getCategoryId());
    }
}
