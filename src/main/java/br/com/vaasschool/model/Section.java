package br.com.vaasschool.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor(onConstructor = @__(@Deprecated))
@Entity
public class Section implements Comparable<Section>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome da seção precisa ser preenchida")
    private String name;
    @NotBlank(message = "O código da seção é obrigatório.")
    @Pattern(regexp = "([a-z0-9^-]+)", message = "Insira um código válido. O código deve conter apenas letras minúsculas, números e hífen (-).")
    private String code;
    private Boolean active = false;
    private Boolean test = false;
    @Column(name = "order_visualization")
    private Integer order;
    @NotNull(message = "A seção deve ter um curso associado.")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public Section(String name, String code, Course course) {
        this.name = name;
        this.code = code;
        this.course = course;
    }

    public String getCode() {
        return code;
    }

    @Override
    public int compareTo(Section anotherSection) {
        return this.order.compareTo(anotherSection.order);
    }
}
