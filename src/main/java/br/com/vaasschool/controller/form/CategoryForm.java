package br.com.vaasschool.controller.form;

import br.com.vaasschool.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
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

    public Category toModel() {
        return new Category(name, code, description, explanatoryGuide, order, active, imagePath, colorCode);
    }
}