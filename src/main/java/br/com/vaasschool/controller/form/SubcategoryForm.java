package br.com.vaasschool.controller.form;

import br.com.vaasschool.model.Category;
import br.com.vaasschool.model.Subcategory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class SubcategoryForm {

    private Long id;
    @NotBlank(message = "O nome da subcategoria precisa ser preenchida.")
    private String name;
    @NotBlank(message = "O código da subcategoria é obrigatório.")
    @Pattern(regexp = "([a-z0-9^-]+)", message = "Insira um código válido. O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;
    private String description;
    private String explanatoryGuide;
    private boolean active = false;
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

    public Subcategory toModel(Category category) {
        return new Subcategory(name, code, description, explanatoryGuide, active, order, category);
    }
}
